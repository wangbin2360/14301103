package dev.edu.javaee.spring.aop.support;

import java.util.List;

import dev.edu.javaee.spring.aop.Advice;
import dev.edu.javaee.spring.aop.ClassFilter;
import dev.edu.javaee.spring.aop.Pointcut;

public class JdkRegexpMethodPointcutAdvisor extends AbstractPointcutAdvisor {
    private List<String> patterns;
    private Advice advice;
	private final JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
	@Override
	public Pointcut getPointcut() {
		return this.pointcut;
	}

	public void setClassFilter(ClassFilter classFilter) {
		this.pointcut.setClassFilter(classFilter);
	}
	
	/*public void setPattern(String mappedName) {
		this.pointcut.setPattern(mappedName);
	}*/
	
	public void setPatterns(List<String> patterns) {
		this.pointcut.setPatterns(patterns);
	}

}
