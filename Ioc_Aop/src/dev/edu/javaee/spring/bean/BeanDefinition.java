package dev.edu.javaee.spring.bean;

import java.util.LinkedList;
import java.util.List;

public class BeanDefinition {
	private Object bean;
	
	private Class<?> beanClass;
	
	private String beanClassName;
	
	private List<String> proxyAdvisorList = new LinkedList<>();
    private String AdvisorListName;
	public String getAdvisorListName() {
		return AdvisorListName;
	}

	public void setAdvisorListName(String advisorListName) {
		AdvisorListName = advisorListName;
	}

	public List<String> getProxyAdvisorList() {
		return proxyAdvisorList;
	}

	public void setProxyAdvisorList(List<String> proxyAdvisorList) {
		this.proxyAdvisorList = proxyAdvisorList;
	}

	private PropertyValues propertyValues;

	public PropertyValues getPropertyValues() {
		return propertyValues;
	}

	public void setPropertyValues(PropertyValues propertyValues) {
		this.propertyValues = propertyValues;
	}

	public Class<?> getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(Class<?> beanClass) {
		this.beanClass = beanClass;
	}

	public String getBeanClassName() {
		return beanClassName;
	}

	public void setBeanClassName(String beanClassName) {
		this.beanClassName = beanClassName;
	}

	public Object getBean() {
		return bean;
	}

	public void setBean(Object bean) {
		this.bean = bean;
	}
	
}
