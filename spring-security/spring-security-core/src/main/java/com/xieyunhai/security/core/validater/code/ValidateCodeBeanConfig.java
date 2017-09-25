package com.xieyunhai.security.core.validater.code;

import com.xieyunhai.security.core.properties.SecurityProperties;
import com.xieyunhai.security.core.validater.code.image.ImageValidateCodeGenerator;
import com.xieyunhai.security.core.validater.code.sms.DefaultSmsCodeSender;
import com.xieyunhai.security.core.validater.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author admin
 * @since 2017/9/25 9:38
 */
@Configuration
public class ValidateCodeBeanConfig {

	@Autowired
	private SecurityProperties securityProperties;

	@Bean
	@ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
	public ValidateCodeGenerator imageValidateCodeGenerator() {
		ImageValidateCodeGenerator imageValidateCodeGenerator = new ImageValidateCodeGenerator();
		imageValidateCodeGenerator.setSecurityProperties(securityProperties);
		return imageValidateCodeGenerator;
	}

	@Bean
	@ConditionalOnMissingBean(SmsCodeSender.class)
	public SmsCodeSender smsCodeSender() {
		return new DefaultSmsCodeSender();
	}
}
