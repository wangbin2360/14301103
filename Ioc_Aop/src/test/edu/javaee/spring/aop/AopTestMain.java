package test.edu.javaee.spring.aop;

import dev.edu.javaee.spring.factory.BeanFactory;
import dev.edu.javaee.spring.factory.XMLBeanFactory;
import dev.edu.javaee.spring.resource.LocalFileResource;

public class AopTestMain {
	public static void main(String[] args) {
        LocalFileResource resource = new LocalFileResource("aop.xml");
		BeanFactory beanFactory = new XMLBeanFactory(resource);
		System.out.println("��֪ͨʹ�÷�����ƥ�䣬ǰ֪ͨʹ��������ʽƥ��");
		System.out.println("���ַ������к�֪ͨ");
		System.out.println("dummyFoo������ǰ֪ͨ");
		System.out.println("printFooʹ�����ֺ�֪ͨ");
	    FooInterface foo = (FooInterface)beanFactory.getBean("foo");
	  
	    foo.printFoo();
	    System.out.println("");
	    foo.dummyFoo();
	  }

}


