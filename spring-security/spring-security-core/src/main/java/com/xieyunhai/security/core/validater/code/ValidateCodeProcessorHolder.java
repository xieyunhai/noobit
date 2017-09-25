package com.xieyunhai.security.core.validater.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author admin
 * @since 2017/9/25 17:26
 */
@Component
public class ValidateCodeProcessorHolder {
	@Autowired
	private Map<String, ValidateCodeProcessor> validateCodeProcessors;

	/**
	 * 通过 type 查找对应的 ValidateCodeProcessor 处理器
	 * @param type type
	 * @return 处理器
	 */
	public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
		return findValidateCodeProcessor(type.toString().toLowerCase());
	}

	public ValidateCodeProcessor findValidateCodeProcessor(String type) {
		String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
		ValidateCodeProcessor processor = validateCodeProcessors.get(name);
		if (processor == null) {
			throw new ValidateCodeException("验证码处理器" + name + "不存在");
		}
		return processor;
	}
}
