package dev.edu.javaee.spring.aop.support;

import dev.edu.javaee.spring.aop.MethodBeforeAdvice;
import dev.edu.javaee.spring.aop.MethodInterceptor;
import dev.edu.javaee.spring.aop.framework.MethodInvocation;

public class MethodBeforeAdviceInterceptor implements MethodInterceptor {

	private MethodBeforeAdvice advice;
	
	public MethodBeforeAdviceInterceptor(MethodBeforeAdvice advice)
	{
		this.advice = advice;
	}
	
	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		advice.before(methodInvocation.getMethod(), methodInvocation.getArguments(), methodInvocation.getThis());
		return methodInvocation.proceed();
	}

}
