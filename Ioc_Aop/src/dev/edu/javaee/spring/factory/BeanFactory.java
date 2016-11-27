package dev.edu.javaee.spring.factory;

import dev.edu.javaee.spring.bean.BeanDefinition;

public interface BeanFactory {
	Object getBean(String beanName);
	void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
