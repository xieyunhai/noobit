package com.xieyunhai.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * @author admin
 * @since 2017/9/22 13:27
 */
@Slf4j
public class TimeFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("filter init...");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		log.info("filter start");
		long start = new Date().getTime();
		filterChain.doFilter(servletRequest, servletResponse);
		log.info("filter takes: {}", new Date().getTime() - start);
		log.info("filter end");
	}

	@Override
	public void destroy() {
		log.info("filter destroy...");
	}
}
