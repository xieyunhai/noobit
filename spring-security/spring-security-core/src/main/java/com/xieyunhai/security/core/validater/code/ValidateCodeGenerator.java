package com.xieyunhai.security.core.validater.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author admin
 * @since 2017/9/25 9:26
 */
public interface ValidateCodeGenerator {
	ValidateCode generate(ServletWebRequest request);
}
