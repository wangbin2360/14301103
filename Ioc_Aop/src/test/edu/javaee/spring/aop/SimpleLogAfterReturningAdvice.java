package test.edu.javaee.spring.aop;

import java.lang.reflect.Method;

import dev.edu.javaee.spring.aop.AfterReturningAdvice;

public class SimpleLogAfterReturningAdvice implements AfterReturningAdvice {

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		System.out.println("The method " +method.getName()+" Finish");
	}

}
