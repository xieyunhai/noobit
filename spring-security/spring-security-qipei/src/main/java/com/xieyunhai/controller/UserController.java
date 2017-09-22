package com.xieyunhai.controller;

import com.xieyunhai.domain.User;
import com.xieyunhai.exception.UserNotExistException;
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
	@PostMapping
	public String hello(@Valid @RequestBody User user, BindingResult bindingResult) {

//		throw new UserNotExistException(2);
		return "Hello Spring Security!";
	}
}
