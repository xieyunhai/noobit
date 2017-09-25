package com.xieyunhai.security.core.authentication.mobile;

import com.xieyunhai.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author admin
 * @since 2017/9/25 15:40
 */
@Component
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,
	HttpSecurity> {

	@Autowired
	private SecurityProperties securityProperties;
	@Autowired
	private AuthenticationSuccessHandler qipeiAuthenticationSuccessHandler;
	@Autowired
	private AuthenticationFailureHandler qipeiAuthenticationFailureHandler;
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public void configure(HttpSecurity http) throws Exception {

		SmsCodeAuthenticationFilter filter = new SmsCodeAuthenticationFilter();
		filter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
		filter.setAuthenticationSuccessHandler(qipeiAuthenticationSuccessHandler);
		filter.setAuthenticationFailureHandler(qipeiAuthenticationFailureHandler);

		SmsCodeAuthenticationProvider provider = new SmsCodeAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		http
			.authenticationProvider(provider)
			.addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class);

	}
}
