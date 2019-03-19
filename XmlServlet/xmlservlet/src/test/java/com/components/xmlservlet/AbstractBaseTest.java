package com.components.xmlservlet;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.components.xmlservlet.api.XmlServiceRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AbstractBaseTest {

	public static final String XML_REQUEST = "<?xml version=\"1.0\"?><XmlServiceRequest><requestId>ABC001</requestId><requestType>CREATE</requestType><emailAddress>abc@def.com</emailAddress><password>1234</password></XmlServiceRequest>";
	
	public static final XmlServiceRequest SERVICEREQUEST = new XmlServiceRequest("ABC001", "CREATE", "abc@def.com", "1234");

}
