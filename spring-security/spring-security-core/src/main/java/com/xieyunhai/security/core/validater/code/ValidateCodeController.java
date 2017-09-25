package com.xieyunhai.security.core.validater.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class ValidateCodeController {
	static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

	@Autowired
	private Map<String, ValidateCodeProcessor> validateCodeProcessors;

	@GetMapping("/code/{type}")
	public void createImageCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type)
		throws Exception {
		validateCodeProcessors.get(type + "CodeProcessor").create(new ServletWebRequest(request, response));
	}


}
