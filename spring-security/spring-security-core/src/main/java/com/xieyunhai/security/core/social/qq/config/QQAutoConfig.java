package com.xieyunhai.security.core.social.qq.config;

import com.xieyunhai.security.core.properties.QQProperties;
import com.xieyunhai.security.core.properties.SecurityProperties;
import com.xieyunhai.security.core.social.qq.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @author admin
 * @since 2017/9/26 16:31
 */
@Configuration
@ConditionalOnProperty(prefix = "qipei.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;

	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		QQProperties qq = securityProperties.getSocial().getQq();
		return new QQConnectionFactory(qq.getProviderId(), qq.getAppId(), qq.getAppSecret());
	}
}
