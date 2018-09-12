package com.cc.buildingReform.Aspect;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import com.cc.buildingReform.form.ResultMessage;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cc.buildingReform.Annotation.Permissions;

public class Aop {
	@Autowired(required = true)
	private HttpServletRequest request;

	private static Logger log = LoggerFactory.getLogger(Aop.class);

	public void before(JoinPoint joinPoint) throws Throwable {
		
	}

	public Object around(ProceedingJoinPoint jp) throws Throwable {
		try {
			MethodSignature signature = (MethodSignature) jp.getSignature();
			Method method = signature.getMethod();
			
			if (method.isAnnotationPresent(Permissions.class)) {
				Permissions permissions = method.getAnnotation(Permissions.class);
				Object u = request.getSession().getAttribute(permissions.target());
				if (u == null) {
					if (!permissions.isJSON()) {
						if (permissions.url().equals("")) {
							return null;
						} else {
							return permissions.url();
						}
					}
					else {
						return new ResultMessage().setLogged(true).setMessage("未登录或登录超时！");
					}
				}
				else {
					if (!permissions.isJSON()) {
						Integer mid = 0;
						if (jp.getArgs().length > 0) {
							mid = (Integer) jp.getArgs()[0];
						}

						if (mid != 0) {
//						if (!((User)u).getRole().getPower().contains("," + mid + ",")) {
//							return null;
//						}
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("/aop/around", e.getMessage());
		}
		
		return jp.proceed();
	}
	
	public void after(JoinPoint joinPoint) throws Throwable {
		
	}
}