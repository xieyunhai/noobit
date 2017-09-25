package com.xieyunhai.security.core.authentication.mobile;

import lombok.Data;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author admin
 * @since 2017/9/25 15:27
 */
@Data
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

	private UserDetailsService userDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;
		UserDetails user = userDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());
		if (user == null) {
			throw new InternalAuthenticationServiceException("无法获取用户信息");
		}

		SmsCodeAuthenticationToken authenticationTokenResult = new SmsCodeAuthenticationToken(user, user
			.getAuthorities());
		authenticationTokenResult.setDetails(authenticationToken.getDetails());
		return authenticationTokenResult;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return SmsCodeAuthenticationToken.class.isAssignableFrom(aClass);
	}
}
