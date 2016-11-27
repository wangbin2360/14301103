package test.edu.javaee.spring.aop;

import java.util.List;

import dev.edu.javaee.spring.aop.Advice;
import dev.edu.javaee.spring.aop.Advisor;
import dev.edu.javaee.spring.aop.framework.AdvisedSupport;
import dev.edu.javaee.spring.aop.framework.JdkDynamicAopProxy;



public class ProxyFactoryBean {
	
    private String proxyInterfaces;
	private Object target;
	private List<Advisor> advisor;
	public String getProxyInterfaces() {
		return proxyInterfaces;
	}
	public void setProxyInterfaces(String proxyInterfaces) {
		this.proxyInterfaces = proxyInterfaces;
	}
	public Object getTarget() {
		return target;
	}
	public void setTarget(Object target) {
		this.target = target;
	}
	
	public List<Advisor> getAdvisor() {
		return advisor;
	}
	
	public void setAdvisor(List<Advisor> advisor) {
		this.advisor = advisor;
	}
	public Object getProxy() {
	AdvisedSupport advicesupport = new AdvisedSupport();
	if(advisor!=null){
        advicesupport.addAdvisors(advisor);
	}
       Class<?>[] interfaces = target.getClass().getInterfaces();
       int i=0;
       
        for(Class<?> Interface : interfaces){
        	
        	String str[]=Interface.getName().split("\\.");    	
        	if(str[str.length-1].equals(proxyInterfaces)){		
       		  advicesupport.setInterfaces(Interface);
        		break;
        	}
        	i++;
        }
		advicesupport.setInterfaces(target.getClass().getInterfaces()[i]);
		advicesupport.setTarget(target);
		return new JdkDynamicAopProxy(advicesupport).getProxy();
		
	}
	
	

}
