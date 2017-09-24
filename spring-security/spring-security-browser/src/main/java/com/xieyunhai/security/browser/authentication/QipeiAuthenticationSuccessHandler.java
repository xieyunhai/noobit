package com.xieyunhai.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xieyunhai.security.core.properties.LoginType;
import com.xieyunhai.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("qipeiAuthenticationSuccessHandler")
@Slf4j
public class QipeiAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    /* spring mvc 在启动时自动注册，可以设置转换数据为 json */
    private final ObjectMapper objectMapper;
    private final SecurityProperties securityProperties;

    @Autowired
    public QipeiAuthenticationSuccessHandler(ObjectMapper objectMapper, SecurityProperties securityProperties) {
        this.objectMapper = objectMapper;
        this.securityProperties = securityProperties;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("登录成功...");

        if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
