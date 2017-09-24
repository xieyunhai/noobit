package com.xieyunhai.security.core.validater.code;

import org.springframework.security.core.AuthenticationException;

public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = 6273410983831469588L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
