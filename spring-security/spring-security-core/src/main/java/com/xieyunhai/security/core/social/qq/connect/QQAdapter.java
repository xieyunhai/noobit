package com.xieyunhai.security.core.social.qq.connect;

import com.xieyunhai.security.core.social.qq.api.QQ;
import com.xieyunhai.security.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @author admin
 * @since 2017/9/26 15:00
 */
public class QQAdapter implements ApiAdapter<QQ> {
	@Override
	public boolean test(QQ qq) {
		return true;
	}

	@Override
	public void setConnectionValues(QQ api, ConnectionValues values) {
		QQUserInfo userInfo = api.getUserInfo();
		values.setDisplayName(userInfo.getNickname());
		values.setImageUrl(userInfo.getFigureurl_qq_1());
		values.setProfileUrl(null);
		values.setProviderUserId(userInfo.getOpenId());
	}

	@Override
	public UserProfile fetchUserProfile(QQ qq) {
		return null;
	}

	@Override
	public void updateStatus(QQ qq, String message) {

	}
}
