package dev.edu.javaee.spring.aop;

public interface Pointcut {
	ClassFilter getClassFilter();
	MethodMatcher getMethodMatcher();
}
