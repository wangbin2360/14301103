package test.edu.javaee.spring.aop;

import java.lang.reflect.Method;

import dev.edu.javaee.spring.aop.AfterReturningAdvice;

public class SecondAfterReturningAdvice implements AfterReturningAdvice {

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		System.out.println("go go go");
	}

}
