package dev.edu.javaee.spring.aop.support;

import java.lang.reflect.Method;

import dev.edu.javaee.spring.aop.MethodMatcher;

public abstract class StaticMethodMatcher implements MethodMatcher {
	public boolean mathces(Method method, Class<?> targetClass, Object... args)
	{
		throw new UnsupportedOperationException("Illegal MethodMatcher usage");
	}
	public boolean isRuntime()
	{
		return false;
	}
}
