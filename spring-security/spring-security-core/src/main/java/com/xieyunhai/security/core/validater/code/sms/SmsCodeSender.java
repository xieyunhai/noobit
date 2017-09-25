package com.xieyunhai.security.core.validater.code.sms;

/**
 * @author admin
 * @since 2017/9/25 11:04
 */
public interface SmsCodeSender {
	void send(String mobile, String code);
}
