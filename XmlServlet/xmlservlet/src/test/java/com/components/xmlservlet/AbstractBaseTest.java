package com.components.xmlservlet;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.components.xmlservlet.api.XmlServiceRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AbstractBaseTest {

	public static final String XML_REQUEST = "<XmlServiceRequest><requestId>ABC001</requestId><type>CREATE</type><emailAddress>abc@def.com</emailAddress><password>1234</password></XmlServiceRequest>";
	
	public static final XmlServiceRequest SERVICEREQUEST = new XmlServiceRequest("ABC001", "CREATE", "abc@def.com", "1234");

}
