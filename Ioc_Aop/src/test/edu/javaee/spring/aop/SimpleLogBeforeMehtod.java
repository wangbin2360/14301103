package test.edu.javaee.spring.aop;

import java.lang.reflect.Method;

import dev.edu.javaee.spring.aop.MethodBeforeAdvice;

public class SimpleLogBeforeMehtod implements MethodBeforeAdvice{

	@Override
	public void before(Method method, Object[] args, Object target) {
		System.out.println("The method "+method.getName()+" Start");
	}

}
