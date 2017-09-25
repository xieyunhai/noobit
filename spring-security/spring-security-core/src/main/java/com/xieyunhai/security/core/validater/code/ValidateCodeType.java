package com.xieyunhai.security.core.validater.code;


import com.xieyunhai.security.core.properties.SecurityConstants;

/**
 * @author admin
 * @since 2017/9/25 16:53
 */
public enum ValidateCodeType {
	/**
	 * 短信验证码
	 */
	SMS {
		@Override
		public String getParamNameOnValidate() {
			return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
		}
	},

	IMAGE {
		@Override
		public String getParamNameOnValidate() {
			return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
		}
	};

	/**
	 * 校验时从请求中获取参数的名字
	 * @return 参数名字
	 */
	public abstract String getParamNameOnValidate();
}
