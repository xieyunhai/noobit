package com.xieyunhai.security.core.validater.code.sms;

import com.xieyunhai.security.core.properties.SecurityProperties;
import com.xieyunhai.security.core.validater.code.ValidateCode;
import com.xieyunhai.security.core.validater.code.ValidateCodeGenerator;
import lombok.Data;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author admin
 * @since 2017/9/26 10:49
 */
@Component("smsValidateCodeGenerator")
@Data
public class SmsValidateCodeGenerator implements ValidateCodeGenerator {
	@Autowired
	private SecurityProperties securityProperties;

	@Override
	public ValidateCode generate(ServletWebRequest request) {
		String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
		return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
	}
}
