package com.xieyunhai.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "qipei.security")
public class SecurityProperties {
    // 这里的参数一定要和 application.yml 中的名称对应， 或者用
    private BrowserProperties browser = new BrowserProperties();

    private ValidateCodeProperties code = new ValidateCodeProperties();
}
