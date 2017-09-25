package com.xieyunhai.security.core.validater.code;

import com.xieyunhai.security.core.properties.SecurityProperties;
import com.xieyunhai.security.core.validater.code.image.ImageCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.xieyunhai.security.core.validater.code.ValidateCodeProcessor.SESSION_KEY_PREFIX;

@EqualsAndHashCode(callSuper = true)
@Data
public class SmsCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private Set<String> urls = new HashSet<>();

    private SecurityProperties securityProperties;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode()
            .getSms().getUrl(), ",");
        if (ArrayUtils.isNotEmpty(configUrls)) {
            urls.addAll(Arrays.asList(configUrls));
        }
        urls.add("/authentication/mobile");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Optional<String> action = urls.stream()
            .filter(url -> antPathMatcher.match(url, request.getRequestURI()))
            .findAny();

        /* note: 判断是否存在需要使用验证码过滤的 url, 并且请求方式是 post */
        if (action.isPresent() && StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {
            try {
                validate(new ServletWebRequest(request));
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        ValidateCode codeInSession = (ValidateCode) sessionStrategy.getAttribute(request, SESSION_KEY_PREFIX + "SMS");
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "smsCode");

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }

        if (codeInSession == null) {
            throw new ValidateCodeException("验证码不存在");
        }

        if (codeInSession.isExpired()) {
            sessionStrategy.removeAttribute(request, SESSION_KEY_PREFIX + "SMS");
            throw new ValidateCodeException("验证码已过期");
        }

        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }

        sessionStrategy.removeAttribute(request, SESSION_KEY_PREFIX + "SMS");
    }
}
