package com.xieyunhai.security.core.authentication;

import com.xieyunhai.security.core.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author admin
 * @since 2017/9/26 10:34
 */
public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	protected AuthenticationSuccessHandler qipeiAuthenticationSuccessHandler;
	@Autowired
	protected AuthenticationFailureHandler qipeiAuthenticationFailureHandler;

	protected void applyUsernamePasswordAuthenticationConfig(HttpSecurity http) throws Exception {
		http
			.formLogin()
			.loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
			.loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
			.successHandler(qipeiAuthenticationSuccessHandler)
			.failureHandler(qipeiAuthenticationFailureHandler);
	}
}
