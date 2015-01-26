package com.spean90.webservice.service.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spean90.webservice.service.IMyService2;

public class TestSpring {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("client-beans.xml");
		IMyService2 myService2 = (IMyService2) context.getBean("myServiceImpl2");
		//myService2.multiply(1, 20);
		System.out.println(myService2.multiply(1, 20));
		
		
	}
}
