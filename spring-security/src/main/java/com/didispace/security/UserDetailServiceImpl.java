package com.didispace.security;

import com.didispace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	private final UserRepository userRepository;

	@Autowired
	public UserDetailServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		com.didispace.domain.User user = userRepository.findByUsername(s);
		if (Objects.isNull(user)) {
			throw new UsernameNotFoundException("找不到账户信息");
		}

		List<GrantedAuthority> list = getRoles(user);
		return new User(user.getUsername(), user.getPassword(), list);
	}

	private List<GrantedAuthority> getRoles(com.didispace.domain.User user) {
		List<GrantedAuthority> list = new ArrayList<>();
		for (String role : user.getRoles().split(",")) {
			// 权限如果前缀是 ROLE_，security就会认为这是个角色信息，而不是权限，例如 ROLE_MENBER 就是 MENBER 角色，CAN_SEND 就是 CAN_SEND 权限
			list.add(new SimpleGrantedAuthority("ROLE_" + role));
		}
		return list;
	}
}
