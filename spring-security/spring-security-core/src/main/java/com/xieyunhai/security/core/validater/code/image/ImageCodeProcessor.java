package com.xieyunhai.security.core.validater.code.image;

import com.xieyunhai.security.core.validater.code.impl.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * @author admin
 * @since 2017/9/25 14:21
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {
	@Override
	protected void send(ServletWebRequest request, ImageCode validateCode) throws Exception {
		ImageIO.write(validateCode.getImage(), "JPEG", request.getResponse().getOutputStream());
	}
}
