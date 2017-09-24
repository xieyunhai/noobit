package com.xieyunhai.security.core.properties;

import lombok.Data;

@Data
public class BrowserProperties {
    private String loginPage = "/login-default.html";

    private LoginType loginType = LoginType.JSON;
}
