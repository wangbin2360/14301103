package dev.edu.javaee.spring.aop;

import dev.edu.javaee.spring.aop.framework.MethodInvocation;

public interface MethodInterceptor {
	Object invoke(MethodInvocation methodInvocation) throws Throwable;
}
