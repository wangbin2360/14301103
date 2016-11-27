package test.edu.javaee.spring.aop;

import dev.edu.javaee.spring.factory.BeanFactory;
import dev.edu.javaee.spring.factory.XMLBeanFactory;
import dev.edu.javaee.spring.resource.LocalFileResource;

public class AopTestMain {
	public static void main(String[] args) {
        LocalFileResource resource = new LocalFileResource("aop.xml");
		BeanFactory beanFactory = new XMLBeanFactory(resource);
		System.out.println("后通知使用方法名匹配，前通知使用正则表达式匹配");
		System.out.println("两种方法都有后通知");
		System.out.println("dummyFoo方法有前通知");
		System.out.println("printFoo使用两种后通知");
	    FooInterface foo = (FooInterface)beanFactory.getBean("foo");
	  
	    foo.printFoo();
	    System.out.println("");
	    foo.dummyFoo();
	  }

}


