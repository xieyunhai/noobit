package com.xieyunhai.security.core.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @author admin
 * @since 2017/9/26 16:28
 */
@Data
public class QQProperties extends SocialProperties {
	private String providerId = "qq";
}
