package com.xieyunhai.security.core.validater.code;

import com.xieyunhai.security.core.properties.SecurityConstants;
import com.xieyunhai.security.core.properties.SecurityProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

@EqualsAndHashCode(callSuper = true)
@Component("validateCodeFilter")
@Slf4j
@Data
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {
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
    /**
     * 验证请求 url 与配置的 url 是否匹配的工具类
     */
    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    /**
     * 存放所有需要校验验证码的 url
     */
    private Map<String, ValidateCodeType> urlMap = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws ServletException {
        // todo: 这里会存在当 key 值相同时，后面的验证会覆盖前面的验证， 导致只能通过一个验证
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
        // note: 屏蔽了 get 方法的验证
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
