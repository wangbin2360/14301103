package dev.edu.javaee.spring.aop.support;

import dev.edu.javaee.spring.aop.MethodInterceptor;
import dev.edu.javaee.spring.aop.ThrowsAdvice;
import dev.edu.javaee.spring.aop.framework.MethodInvocation;

public class ThrowsAdviceInterceptor implements MethodInterceptor {

	private ThrowsAdvice advice;
	
	public ThrowsAdviceInterceptor(ThrowsAdvice advice)
	{
		this.advice = advice;
	}
	
	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		try {
			Object returnValue = methodInvocation.proceed();
			return returnValue;
		}
		catch (Throwable ex) {
			//We didn`t define our exceptionHandler for special exceptionClass, just simply throw the exception.
			throw ex;
		}
	}

}
