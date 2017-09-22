package com.xieyunhai.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author admin
 * @since 2017/9/22 14:05
 */
@Slf4j
@Component
public class TimeInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
		throws Exception {
		log.info("pre handle...");
		log.info("class name: {}", ((HandlerMethod)o).getBean().getClass().getName());
		log.info("method: {}", ((HandlerMethod)o).getMethod().getName());

		httpServletRequest.setAttribute("startTime", new Date().getTime());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
		log.info("post handle...");
		log.info("time interceptor takes: {}",
			new Date().getTime() - (long)httpServletRequest.getAttribute("startTime"));
	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object
		o, Exception e) throws Exception {
		log.info("after completion...");
		log.info("time interceptor takes: {}",
			new Date().getTime() - (long)httpServletRequest.getAttribute("startTime"));

		// note: 这里可以处理异常, 也可以不需要处理, 如果主动抛出异常, 这里会打印日志
	}
}
