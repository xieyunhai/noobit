package com.xieyunhai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author admin
 * @since 2017/9/21 16:08
 */
@SpringBootApplication
@RestController
public class QipeiApplication {
	public static void main(String[] args) {
		SpringApplication.run(QipeiApplication.class, args);
	}
}
