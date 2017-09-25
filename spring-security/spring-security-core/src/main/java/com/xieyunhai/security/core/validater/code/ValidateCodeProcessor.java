package com.xieyunhai.security.core.validater.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author admin
 * @since 2017/9/25 14:11
 */
public interface ValidateCodeProcessor {
	/**
	 * 验证码放入 session 时的前缀
	 */
	String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

	/**
	 * 创建校验码
	 * @param request request
	 * @throws Exception exception
	 */
	void create(ServletWebRequest request) throws Exception;
}
