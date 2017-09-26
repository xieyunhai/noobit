package com.xieyunhai.security.core.validater.code.sms;

import com.xieyunhai.security.core.properties.SecurityConstants;
import com.xieyunhai.security.core.validater.code.ValidateCode;
import com.xieyunhai.security.core.validater.code.impl.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author admin
 * @since 2017/9/26 10:50
 */
@Component("smsValidateCodeProcessor")
public class SmsValidateCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {
	@Autowired
	private SmsCodeSender smsCodeSender;

	@Override
	protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
		String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
		String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
		smsCodeSender.send(mobile, validateCode.getCode());
	}
}
