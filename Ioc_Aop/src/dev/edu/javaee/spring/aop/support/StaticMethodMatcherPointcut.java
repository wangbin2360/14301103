package dev.edu.javaee.spring.aop.support;

import dev.edu.javaee.spring.aop.ClassFilter;
import dev.edu.javaee.spring.aop.MethodMatcher;
import dev.edu.javaee.spring.aop.Pointcut;

public abstract class StaticMethodMatcherPointcut extends StaticMethodMatcher implements Pointcut {
	
	private ClassFilter classFilter;
	
	@Override
	public ClassFilter getClassFilter() {
		return classFilter;
	}
	public void setClassFilter(ClassFilter classFilter) {
		this.classFilter = classFilter;
	}
	
	@Override
	public MethodMatcher getMethodMatcher() {
		return this;
	}
}
