package com.xieyunhai.security.core.social.qq.connect;

import com.xieyunhai.security.core.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @author admin
 * @since 2017/9/26 15:15
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {
	public QQConnectionFactory(String providerId, String appId, String appSecret) {
		super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
	}
}
