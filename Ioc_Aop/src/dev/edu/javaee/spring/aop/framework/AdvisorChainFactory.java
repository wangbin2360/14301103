package dev.edu.javaee.spring.aop.framework;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import dev.edu.javaee.spring.aop.Advisor;
import dev.edu.javaee.spring.aop.AfterReturningAdvice;
import dev.edu.javaee.spring.aop.MethodBeforeAdvice;
import dev.edu.javaee.spring.aop.MethodInterceptor;
import dev.edu.javaee.spring.aop.ThrowsAdvice;
import dev.edu.javaee.spring.aop.support.AfterReturningAdviceInterceptor;
import dev.edu.javaee.spring.aop.support.MethodBeforeAdviceInterceptor;
import dev.edu.javaee.spring.aop.support.PointcutAdvisor;
import dev.edu.javaee.spring.aop.support.ThrowsAdviceInterceptor;

public class AdvisorChainFactory {
	public List<MethodInterceptor> getInterceptor(AdvisedSupport config, Method method, Class<?> targetClass)
	{
		List<MethodInterceptor> interceptorList = new ArrayList<>();
		for(Advisor advisor : config.getAdvisors())
		{
			PointcutAdvisor pointcutAdvisor = (PointcutAdvisor) advisor;
			if(pointcutAdvisor.getPointcut().getMethodMatcher().matches(method, targetClass))
			{
				if(advisor.getAdvice() instanceof MethodBeforeAdvice)
					interceptorList.add(new MethodBeforeAdviceInterceptor((MethodBeforeAdvice) advisor.getAdvice()));
				else if(advisor.getAdvice() instanceof AfterReturningAdvice)
					interceptorList.add(new AfterReturningAdviceInterceptor((AfterReturningAdvice) advisor.getAdvice()));
				else if(advisor.getAdvice() instanceof ThrowsAdvice)
					interceptorList.add(new ThrowsAdviceInterceptor((ThrowsAdvice) advisor.getAdvice()));
			}
		}
		return interceptorList;
	}
}
