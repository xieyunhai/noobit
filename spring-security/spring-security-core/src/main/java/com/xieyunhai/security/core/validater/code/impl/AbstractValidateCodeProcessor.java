package com.xieyunhai.security.core.validater.code.impl;

import com.xieyunhai.security.core.validater.code.ValidateCode;
import com.xieyunhai.security.core.validater.code.ValidateCodeGenerator;
import com.xieyunhai.security.core.validater.code.ValidateCodeProcessor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * @author admin
 * @since 2017/9/25 14:14
 */
public abstract class AbstractValidateCodeProcessor<T extends ValidateCode> implements ValidateCodeProcessor {

	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	/**
	 * 依赖搜索
	 * 收集系统中所有的 {@link ValidateCodeGenerator} 接口的实现
	 */
	@Autowired
	private Map<String, ValidateCodeGenerator> validateCodeGenerators;

	private void save(ServletWebRequest request, T validateCode) {
		sessionStrategy.setAttribute(request, SESSION_KEY_PREFIX + getProcessorType(request), validateCode);
	}

	private String getProcessorType(ServletWebRequest request) {
		return StringUtils.substringAfter(request.getRequest().getRequestURI(), "/code/");
	}

	@Override
	public void create(ServletWebRequest request) throws Exception {
		T validateCode = generate(request);
		save(request, validateCode);
		send(request, validateCode);
	}

	/**
	 * 使用不同生成器生成对应生成校验码
	 * @param request request
	 * @return 校验码
	 */
	@SuppressWarnings("unchecked")
	private T generate(ServletWebRequest request) {
		String type = getProcessorType(request);
		ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(type + "CodeGenerator");
		return (T) validateCodeGenerator.generate(request);
	}

	/**
	 * 发送校验码, 由子类实现
	 * @param request request
	 * @param validateCode 校验码
	 * @throws Exception exception
	 */
	protected abstract void send(ServletWebRequest request, T validateCode) throws Exception;
}
