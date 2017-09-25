package com.xieyunhai.security.core.validater.code.impl;

import com.xieyunhai.security.core.validater.code.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
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

		String type = getValidateCodeType(request).toString().toLowerCase();
		String generatorName = type + ValidateCodeGenerator.class.getSimpleName();
		ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(generatorName);
		if (validateCodeGenerator == null) {
			throw new ValidateCodeException("验证码生成器" + generatorName + "不存在");
		}
		return (T) validateCodeGenerator.generate(request);
	}

	private void save(ServletWebRequest request, T validateCode) {
		sessionStrategy.setAttribute(request, getSessionKey(request), validateCode);
	}

	/**
	 * 发送校验码, 由子类实现
	 * @param request request
	 * @param validateCode 校验码
	 * @throws Exception exception
	 */
	protected abstract void send(ServletWebRequest request, T validateCode) throws Exception;

	@SuppressWarnings("unchecked")
	@Override
	public void validate(ServletWebRequest request) {
		ValidateCodeType type = getValidateCodeType(request);
		String sessionKey = getSessionKey(request);

		T codeInSession = (T) sessionStrategy.getAttribute(request, sessionKey);

		String codeInRequest;
		try {
			codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
				type.getParamNameOnValidate());
		} catch (ServletRequestBindingException e) {
			throw new ValidateCodeException("获取验证码的值失败");
		}

		if (StringUtils.isBlank(codeInRequest)) {
			throw new ValidateCodeException(type + "验证码的值不能为空");
		}

		if (codeInSession == null) {
			throw new ValidateCodeException(type + "验证码不存在");
		}

		if (codeInSession.isExpired()) {
			sessionStrategy.removeAttribute(request, sessionKey);
			throw new ValidateCodeException(type + "验证码已过期");
		}

		if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
			throw new ValidateCodeException(type + "验证码不匹配");
		}
		sessionStrategy.removeAttribute(request, sessionKey);
	}

	/**
	 * 根据请求的url获取校验码的类型
	 *
	 * @param request
	 * @return
	 */
	private ValidateCodeType getValidateCodeType(ServletWebRequest request) {
		String type = StringUtils.substringBefore(getClass().getSimpleName(), "ValidateCodeProcessor");
		return ValidateCodeType.valueOf(type.toUpperCase());
	}

	/**
	 * 构建验证码放入session时的key
	 *
	 * @param request
	 * @return
	 */
	private String getSessionKey(ServletWebRequest request) {
		return SESSION_KEY_PREFIX + getValidateCodeType(request).toString().toUpperCase();
	}
}
