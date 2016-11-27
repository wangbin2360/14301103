package dev.edu.javaee.spring.aop.support;

import dev.edu.javaee.spring.aop.Advisor;
import dev.edu.javaee.spring.aop.Pointcut;

public interface PointcutAdvisor extends Advisor {
	Pointcut getPointcut();
}
