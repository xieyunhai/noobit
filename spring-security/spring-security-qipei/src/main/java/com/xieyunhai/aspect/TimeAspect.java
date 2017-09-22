package com.xieyunhai.aspect;

import com.xieyunhai.util.ParamsUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * @author admin
 * @since 2017/9/22 14:45
 */
@Aspect
@Component
@Slf4j
public class TimeAspect {
	@Around("execution(* com.xieyunhai.controller.UserController.*(..))")
	public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
		log.info("aspect start");
		long start = new Date().getTime();
		String className = pjp.getTarget().getClass().getName();
		String methodName = pjp.getSignature().getName();
		log.info(className + " 的 " + methodName + " 执行了 ");
		String[] fields = ParamsUtil.getFieldsName(className, methodName);
		Object[] values = pjp.getArgs();

		Map<String, Object> paramsMap = ParamsUtil.getParams(fields, values);
		log.info("入参为: {}", paramsMap);
		Object o;
		o = pjp.proceed();
		log.info("filter takes: {}", new Date().getTime() - start);
		log.info("aspect end");
		return o;
	}
}
