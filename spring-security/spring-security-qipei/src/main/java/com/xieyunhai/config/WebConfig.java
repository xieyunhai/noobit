package com.xieyunhai.config;

import com.xieyunhai.filter.TimeFilter;
import com.xieyunhai.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @since 2017/9/22 13:59
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	private final TimeInterceptor timeInterceptor;

	@Autowired
	public WebConfig(TimeInterceptor timeInterceptor) {
		this.timeInterceptor = timeInterceptor;
	}

	@Bean
	public FilterRegistrationBean timeFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		TimeFilter timeFilter = new TimeFilter();
		filterRegistrationBean.setFilter(timeFilter);

		List<String> urls = new ArrayList<>();
		urls.add("/*");

		filterRegistrationBean.setUrlPatterns(urls);

		return filterRegistrationBean;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(timeInterceptor);
	}
}
