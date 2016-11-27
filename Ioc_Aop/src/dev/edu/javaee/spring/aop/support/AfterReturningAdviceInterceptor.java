package dev.edu.javaee.spring.aop.support;

import dev.edu.javaee.spring.aop.AfterReturningAdvice;
import dev.edu.javaee.spring.aop.MethodInterceptor;
import dev.edu.javaee.spring.aop.framework.MethodInvocation;

public class AfterReturningAdviceInterceptor implements MethodInterceptor {

	private AfterReturningAdvice advice;
	
	public AfterReturningAdviceInterceptor(AfterReturningAdvice advice)
	{
		this.advice = advice;
	}
	
	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		Object returnValue = methodInvocation.proceed();
		advice.afterReturning(returnValue, methodInvocation.getMethod(), methodInvocation.getArguments(), methodInvocation.getThis());
		return returnValue;
	}

}
