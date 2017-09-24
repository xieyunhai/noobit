package com.xieyunhai.controller;

import com.xieyunhai.domain.User;
import com.xieyunhai.exception.UserNotExistException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author admin
 * @since 2017/9/21 17:10
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@GetMapping("/me")
	public Object getCurrentUser(Authentication authentication) {
		return authentication;
	}

	@GetMapping("/me/user")
	public Object getCurrentUser(@AuthenticationPrincipal UserDetails user) {
		/* note: 只拿到 principal 中的属性 */
		return user;
	}

	@GetMapping
	public String hello() {

//		throw new UserNotExistException(2);
		return "Hello Spring Security!";
	}

	@GetMapping("/{id}")
	public String detail(@PathVariable String id) {
		return "Hello Spring Security!" + id;
	}
}
