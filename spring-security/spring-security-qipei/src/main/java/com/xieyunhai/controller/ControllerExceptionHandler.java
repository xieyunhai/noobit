package com.xieyunhai.controller;

import com.xieyunhai.exception.UserNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author admin
 * @since 2017/9/22 12:02
 */
@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

	@ExceptionHandler(com.xieyunhai.exception.UserNotExistException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, Object> handleUserNotExistException(UserNotExistException ex) {
		log.warn("user not exist exception handler");
		// note: 只应该在这里打印并处理异常
		log.error("exception: {}", ex);
		Map<String, Object> result = new HashMap<>();
		result.put("id", ex.getId());
		result.put("message", ex.getMessage());
		return result;
	}
}
