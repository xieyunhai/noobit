package com.xieyunhai.security.core.validater.code;

import com.xieyunhai.security.core.properties.SecurityConstants;
import com.xieyunhai.security.core.properties.SecurityProperties;
import com.xieyunhai.security.core.validater.code.image.ImageCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
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
import java.util.*;
import java.util.stream.Stream;

import static com.xieyunhai.security.core.validater.code.ValidateCodeProcessor.SESSION_KEY_PREFIX;

@EqualsAndHashCode(callSuper = true)
@Data
@Component("validateCodeFilter")
@Slf4j
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {
    /**
     * 验证请求 url 与配置的 url 是否匹配的工具类
     */
    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    /**
     * 存放所有需要校验验证码的 url
     */
    private Map<String, ValidateCodeType> urlMap = new HashMap<>();

    /**
     * 系统中的校验码处理器
     */
    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;
    /**
     * 验证码校验失败的处理器
     */
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    /**
     * 系统配置信息
     */
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void afterPropertiesSet() throws ServletException {
        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM, ValidateCodeType.IMAGE);
        addUrlToMap(securityProperties.getCode().getImage().getUrl(), ValidateCodeType.IMAGE);

        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, ValidateCodeType.SMS);
        addUrlToMap(securityProperties.getCode().getImage().getUrl(), ValidateCodeType.SMS);
    }

    /**
     * 将系统中配置的需要校验验证码的 url 根据校验的类型放入 map
     * @param urlString 配置中的字符串
     * @param type 验证类型
     */
    private void addUrlToMap(String urlString, ValidateCodeType type) {
        if (StringUtils.isNotBlank(urlString)) {
            String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString, ",");
            Stream.of(urls).forEach(url -> urlMap.put(url, type));
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Optional<ValidateCodeType> optional = getValidateCodeType(request);
        if (optional.isPresent()) {
            log.info("校验请求( {} )中的验证码, 验证码类型 {}", request.getRequestURI(), optional.get());
            try {
                validateCodeProcessorHolder
                    .findValidateCodeProcessor(optional.get())
                    .validate(new ServletWebRequest(request, response));
                log.info("验证码校验通过");
            } catch (ValidateCodeException exception) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, exception);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 获取校验码的类型
     * @param request request
     * @return Optional
     */
    private Optional<ValidateCodeType> getValidateCodeType(HttpServletRequest request) {
        Optional<String> first = Optional.empty();
        if (!StringUtils.equalsIgnoreCase(request.getMethod(), "get")) {
            Set<String> urls = urlMap.keySet();
            first = urls.stream()
                .filter(url -> antPathMatcher.match(url, request.getRequestURI()))
                .findAny();
        }
//        if (first.isPresent()) {
//            return Optional.of(urlMap.get(first.get()));
//        } else {
//            return Optional.empty();
//        }
        return first.map(s -> urlMap.get(s));
    }
}
