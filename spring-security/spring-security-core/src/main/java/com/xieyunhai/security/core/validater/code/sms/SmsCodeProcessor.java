package com.xieyunhai.security.core.validater.code.sms;

import com.xieyunhai.security.core.validater.code.ValidateCode;
import com.xieyunhai.security.core.validater.code.image.ImageCode;
import com.xieyunhai.security.core.validater.code.impl.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * @author admin
 * @since 2017/9/25 14:21
 */
@Component("smsCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {
	@Autowired
	private SmsCodeSender smsCodeSender;
	@Override
	protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
		String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), "mobile");
		smsCodeSender.send(mobile, validateCode.getCode());
	}
}
