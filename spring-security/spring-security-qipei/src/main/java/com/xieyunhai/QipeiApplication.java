package com.xieyunhai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author admin
 * @since 2017/9/21 16:08
 */
@SpringBootApplication
@RestController
@EnableSwagger2
public class QipeiApplication {
	public static void main(String[] args) {
		SpringApplication.run(QipeiApplication.class, args);
	}
}
