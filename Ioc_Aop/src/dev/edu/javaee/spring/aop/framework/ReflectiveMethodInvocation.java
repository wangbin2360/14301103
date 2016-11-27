package dev.edu.javaee.spring.aop.framework;

import java.lang.reflect.Method;
import java.util.List;

import dev.edu.javaee.spring.aop.MethodInterceptor;

public class ReflectiveMethodInvocation implements MethodInvocation {

	private Object target;
	
	private Method method;

	private Object[] arguments;
	
	private List<MethodInterceptor> interceptors;
	
	private int currentInterceptorIndex = -1;
	
	protected ReflectiveMethodInvocation(
			Object target, Method method, Object[] arguments,
			List<MethodInterceptor> interceptors) {

		this.target = target;
		this.method = method;
		this.arguments = arguments;
		this.interceptors = interceptors;
	}
	
	@Override
	public Object proceed() throws Throwable {
		// TODO Auto-generated method stub
		if (this.currentInterceptorIndex == this.interceptors.size() - 1) {
			return method.invoke(this.target, this.arguments);
		}

		MethodInterceptor interceptor = this.interceptors.get(++this.currentInterceptorIndex);
		return interceptor.invoke(this);
	}

	@Override
	public Method getMethod() {
		// TODO Auto-generated method stub
		return this.method;
	}

	@Override
	public Object[] getArguments() {
		// TODO Auto-generated method stub
		return this.arguments;
	}

	@Override
	public Object getThis() {
		// TODO Auto-generated method stub
		return this.target;
	}

}
