package dev.edu.javaee.spring.aop.framework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import dev.edu.javaee.spring.aop.AopProxy;
import dev.edu.javaee.spring.aop.MethodInterceptor;

public class JdkDynamicAopProxy implements AopProxy, InvocationHandler{

	private AdvisedSupport advised;
	
	public JdkDynamicAopProxy(AdvisedSupport advised)
	{
		this.advised = advised;
	}
	
	public Object getProxy()
	{
		return Proxy.newProxyInstance(
				this.getClass().getClassLoader(),
				new Class[]{advised.getInterfaces()}, 
				this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		
		AdvisorChainFactory advisorChainFactory = new AdvisorChainFactory();
		List<MethodInterceptor> interceptorList = advisorChainFactory.getInterceptor(advised, method, advised.getTargetSource().getTarget().getClass());
		if(interceptorList.size() == 0)
			return method.invoke(advised.getTargetSource().getTarget(), args);
		ReflectiveMethodInvocation invocation = new ReflectiveMethodInvocation(advised.getTargetSource().getTarget(), method, args, interceptorList);
		return invocation.proceed();
	}

}
