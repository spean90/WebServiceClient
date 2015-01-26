package com.spean90.webservice.service.test;

import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class MyIntercept extends AbstractPhaseInterceptor<SoapMessage> {
	
	private String name;
	private String password;
	
	public MyIntercept() {
		super(Phase.PRE_PROTOCOL); //在准备协议化是执行该拦截器
		this.name = "spean";
		this.password = "123456";
		System.out.println(name+"    >>>>>>>>>>    "+password);
	}
	
	/**
	 * <envelop>
	 * 		<header>
	 * 			<user>
	 * 				<name></name>
	 * 				<pasword></password>
	 * 			</user>
	 * 		</header>
	 * 		<body>
	 * 		</body>
	 * </envelop>
	 */

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		List<Header>headers = message.getHeaders();
		Document document = null;;
		try {
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element root = document.createElement("user");
		Element nameEl = document.createElement("name");
		nameEl.setTextContent(name);
		Element passwordEl = document.createElement("password");
		passwordEl.setTextContent(password);
		root.appendChild(nameEl);
		root.appendChild(passwordEl);
		//把验证信息放入message的头部
		headers.add(new Header(new QName("user"), root));
		
		
	}

}
