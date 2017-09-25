package com.xieyunhai.security.core.validater.code.image;

import com.xieyunhai.security.core.validater.code.ValidateCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class ImageCode extends ValidateCode {
    private BufferedImage image;

    /**
     *
     * @param image 验证图片
     * @param code 验证字符串
     * @param expireIn 过期时间, 单位 s
     */
    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code, expireIn);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code, expireTime);
        this.image = image;
    }

    public boolean isExpired() {
        return super.isExpired();
    }
}
