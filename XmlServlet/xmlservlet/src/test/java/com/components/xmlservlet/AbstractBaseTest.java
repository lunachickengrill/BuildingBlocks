package com.components.xmlservlet;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.components.xmlservlet.api.XmlServiceRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AbstractBaseTest {

	public static final String XML_REQUEST = "<?xml version=\"1.0\"?>\r\n" + 
			"<XmlServiceRequest>\r\n" + 
			"  <requestId>ABC001</requestId>\r\n" + 
			"  <requestType>CREATE</requestType>\r\n" + 
			"  <emailAddress>abcd@def.com</emailAddress>\r\n" + 
			"  <password>1234</password>\r\n" + 
			"</XmlServiceRequest>";
	
	public static final XmlServiceRequest SERVICEREQUEST = new XmlServiceRequest("ABC001", "CREATE", "abc@def.com", "1234");

}
