package com.xieyunhai.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author admin
 * @since 2017/9/22 15:48
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserNotExistException extends RuntimeException {
	private static final long serialVersionUID = 8481955374853609878L;

	private Integer id;

	public UserNotExistException(Integer id) {
		super("user not exist...");
		this.id = id;
	}
}
