package com.xieyunhai.security.core.validater.code;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ValidateCode {
    private String code;
    private LocalDateTime expireTime;

    /**
     *
     * @param code 验证字符串
     * @param expireIn 过期时间, 单位 s
     */
    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }


    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
