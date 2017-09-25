package com.xieyunhai.security.browser;

import com.xieyunhai.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.xieyunhai.security.core.properties.SecurityProperties;
import com.xieyunhai.security.core.validater.code.SmsCodeFilter;
import com.xieyunhai.security.core.validater.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;


@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;
	@Autowired
	private AuthenticationSuccessHandler qipeiAuthenticationSuccessHandler;
	@Autowired
	private AuthenticationFailureHandler qipeiAuthenticationFailureHandler;
	@Autowired
	private DataSource dataSource;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
//		tokenRepository.setCreateTableOnStartup(true);
		return tokenRepository;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
		validateCodeFilter.setAuthenticationFailureHandler(qipeiAuthenticationFailureHandler);
		validateCodeFilter.setSecurityProperties(securityProperties);
		validateCodeFilter.afterPropertiesSet();

		SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
		smsCodeFilter.setAuthenticationFailureHandler(qipeiAuthenticationFailureHandler);
		smsCodeFilter.setSecurityProperties(securityProperties);
		smsCodeFilter.afterPropertiesSet();


		http
			.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
			.formLogin()
				.loginPage("/authentication/require")
				.loginProcessingUrl("/authentication/form")
				.successHandler(qipeiAuthenticationSuccessHandler)
				.failureHandler(qipeiAuthenticationFailureHandler)
			.and()
			.rememberMe()
				.tokenRepository(persistentTokenRepository())
				.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
				.userDetailsService(userDetailsService)
			.and()
			.authorizeRequests()
				// 防止进入跳转死循环
				.antMatchers("/authentication/require",
					securityProperties.getBrowser().getLoginPage(),
					"/code/*").permitAll()
				.anyRequest()
				.authenticated()
			.and()
			// 关闭跨站防护
			.csrf()
				.disable()
			.apply(smsCodeAuthenticationSecurityConfig);
	}
}
