package dev.edu.javaee.spring.factory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;


import dev.edu.javaee.spring.aop.Advisor;
import dev.edu.javaee.spring.bean.BeanDefinition;
import dev.edu.javaee.spring.bean.BeanUtil;
import dev.edu.javaee.spring.bean.PropertyValue;
import dev.edu.javaee.spring.bean.PropertyValues;
import dev.edu.javaee.spring.resource.Resource;

public class XMLBeanFactory extends AbstractBeanFactory {

	private String xmlPath;

	private Map<String, Map<String, String>> ref_relation_bean = new HashMap<String, Map<String, String>>();

	public XMLBeanFactory(Resource resource) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
			Document document = dbBuilder.parse(resource.getInputStream());
			NodeList beanList = document.getElementsByTagName("bean");
			for (int i = 0; i < beanList.getLength(); i++) {
				Node bean = beanList.item(i);
				BeanDefinition beandef = new BeanDefinition();
				String beanClassName = bean.getAttributes().getNamedItem("class").getNodeValue();
				String beanName = bean.getAttributes().getNamedItem("id").getNodeValue();

				beandef.setBeanClassName(beanClassName);
				Map<String, String> name_ref_mapping = new HashMap<String, String>();
				try {
					Class<?> beanClass = Class.forName(beanClassName);
					beandef.setBeanClass(beanClass);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				PropertyValues propertyValues = new PropertyValues();

				NodeList propertyList = bean.getChildNodes();
				for (int j = 0; j < propertyList.getLength(); j++) {
					Node property = propertyList.item(j);
					if (property instanceof Element) {
						Element ele = (Element) property;

						String name = ele.getAttribute("name");
						Class<?> type;
						try {
							type = beandef.getBeanClass().getDeclaredField(name).getType();
							Object value = ele.getAttribute("value");
							if (value != null && value.toString().length() > 0) {
								if (type == Integer.class) {
									value = Integer.parseInt((String) value);
								}

								propertyValues.AddPropertyValue(new PropertyValue(name, value));
							} else if (ele.getAttribute("ref") != null
									&& ele.getAttribute("ref").toString().length() > 0) {
								String ref = ele.getAttribute("ref");
								if (ref == null || ref.length() == 0) {
									throw new IllegalArgumentException(
											"Configuration problem: <property> element for property '" + name
													+ "' must specify a ref or value");
								}

								name_ref_mapping.put(name, ref);
								ref_relation_bean.put(beanName, name_ref_mapping);

							} else {
								if (ele.getAttribute("name") != null && ele.getAttribute("name").toString().length() > 0
										&& ele.getAttribute("value").length() == 0) {
									NodeList nodelist = ele.getChildNodes();
									String str="";
									for(int w=0;w<nodelist.getLength();w++){
										if(nodelist.item(w) instanceof Element){
											Element ele1 = (Element) nodelist.item(w);
											str = nodelist.item(w).getNodeName();
										}
									}
									
									if (str.equals("props")) {
										 List<String> listExpression = new LinkedList<String>();
										NodeList proplist = ele.getElementsByTagName("value");
										for (int k = 0; k < proplist.getLength(); k++) {
											Node propnode = proplist.item(k);
											if (propnode instanceof Element) {
												Element propElement = (Element) propnode;
												listExpression.add(propElement.getTextContent());
											}
										}
										propertyValues.AddPropertyValue(new  PropertyValue(name,listExpression));
									}
									
									if(str.equals("list")){
										List<String> proxyAdvisorList =   new LinkedList<>();								
										NodeList advisornodelist = ele.getElementsByTagName("value");
										for(int k=0;k<advisornodelist.getLength();k++){
											Node advisornode = advisornodelist.item(k);
											if (advisornode instanceof Element) {
												Element advisorElement = (Element) advisornode;
												proxyAdvisorList.add(advisorElement.getTextContent());
												
											}
										}
										
										beandef.setProxyAdvisorList(proxyAdvisorList);
										beandef.setAdvisorListName(name);
									}
								}
							}

						} catch (NoSuchFieldException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
				beandef.setPropertyValues(propertyValues);

				this.registerBeanDefinition(beanName, beandef);
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int i = 0;
		while (i < ref_relation_bean.size()) {
			Iterator it = ref_relation_bean.keySet().iterator();

			i++;
			while (it.hasNext()) {

				String beanname = it.next().toString();
				BeanDefinition bean = new BeanDefinition();
				bean = getBeanDefinition(beanname);
				Map<String, String> map = ref_relation_bean.get(beanname);
				Iterator it1 = map.keySet().iterator();
				Iterator it2 = map.values().iterator();
				while (it1.hasNext()) {
					String propertyname = it1.next().toString();
					String ref = it2.next().toString();
					bean.getPropertyValues().AddPropertyValue(new PropertyValue(propertyname, getBean(ref)));
				}
                if(!bean.getProxyAdvisorList().isEmpty()){
                	List<String> advisorStrList = bean.getProxyAdvisorList();
                	List<Object> advisorList = new LinkedList<Object>();
                	for(int k=0;k<advisorStrList.size();k++){
                		advisorList.add(getBean(advisorStrList.get(k)));
                		
                	}
                	bean.getPropertyValues().AddPropertyValue(new PropertyValue(bean.getAdvisorListName(),advisorList));
                }
				this.registerBeanDefinition(beanname, bean);
			}
		}
	}

	@Override
	protected BeanDefinition GetCreatedBean(BeanDefinition beanDefinition) {

		try {
			// set BeanClass for BeanDefinition

			Class<?> beanClass = beanDefinition.getBeanClass();
			// set Bean Instance for BeanDefinition
			Object bean = beanClass.newInstance();

			List<PropertyValue> fieldDefinitionList = beanDefinition.getPropertyValues().GetPropertyValues();
			for (PropertyValue propertyValue : fieldDefinitionList) {

				BeanUtil.invokeSetterMethod(bean, propertyValue.getName(), propertyValue.getValue());
			}

			beanDefinition.setBean(bean);

			return beanDefinition;

		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
