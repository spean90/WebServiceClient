package com.spean90.webservice.service.test;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;

import com.spean90.webservice.service.IMyService2;
import com.spean90.webservice.service.impl.MyService2ImplService;

public class ClientByUseMyInterceptorTest {

	public static void main(String[] args) {
		MyService2ImplService myService2ImplService = new MyService2ImplService();
		IMyService2 myService2 = myService2ImplService.getMyService2ImplPort();
		Client client = ClientProxy.getClient(myService2);
		client.getInInterceptors().add(new LoggingInInterceptor());
		client.getOutInterceptors().add(new MyIntercept("spean","123456"));
		client.getOutInterceptors().add(new LoggingOutInterceptor());
		int i = myService2.multiply(10, 2);
		System.out.println(i);
	}
}
