package com.xieyunhai.security.core.authentication.mobile;

import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 2017/9/26 10:35
 */
@Component
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,
	HttpSecurity> {
	@Autowired
	private AuthenticationSuccessHandler qipeiAuthenticationSuccessHandler;
	@Autowired
	private AuthenticationFailureHandler qipeiAuthenticationFailureHandler;
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		SmsAuthenticationFilter filter = new SmsAuthenticationFilter();
		filter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
		filter.setAuthenticationSuccessHandler(qipeiAuthenticationSuccessHandler);
		filter.setAuthenticationFailureHandler(qipeiAuthenticationFailureHandler);

		SmsAuthenticationProvider provider = new SmsAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);

		http
			.authenticationProvider(provider)
			.addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class);
	}
}
