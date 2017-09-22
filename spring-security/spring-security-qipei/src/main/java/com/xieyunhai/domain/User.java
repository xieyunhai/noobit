package com.xieyunhai.domain;

import com.xieyunhai.validator.MyConstraint;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author admin
 * @since 2017/9/22 11:25
 */
@Data
public class User {
	private Integer id;

	private String username;

	@MyConstraint(message = "hello")
	private String password;
}
