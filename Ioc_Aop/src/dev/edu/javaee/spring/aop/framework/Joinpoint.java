package dev.edu.javaee.spring.aop.framework;

public interface Joinpoint {
	Object proceed() throws Throwable;
	Object getThis();
}
