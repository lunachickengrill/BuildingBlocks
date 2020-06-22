package com.components.xmlservlet;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.components.xmlservlet.api.ServiceRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AbstractBaseTest {

	public static final String SERVICE = "requestService";
	public static final String METHOD = "requestMethod";
	public static final String REQUEST_ID = "0123456789";
	public static final String REQUEST_SERVICE = "basicCustomerService";
	public static final String REQUEST_METHOD = "createCustomer";
	public static final String REQUEST_EMAIL = "abdcefg@kapschcs.at";
	public static final String REQUEST_PWD = "kcs123";

	public static final String XML_REQUEST = "<ServiceRequest>\r\n" + 
			"  <requestId>123</requestId>\r\n" + 
			"  <requestService>basicCustomerService</requestService>\r\n" + 
			"  <requestMethod>createCustomer</requestMethod>\r\n" + 
			"  <emailAddress>emailAdress</emailAddress>\r\n" + 
			"  <password>password</password>\r\n" + 
			"</ServiceRequest>";

	public static final ServiceRequest SERVICEREQUEST = new ServiceRequest(REQUEST_ID, REQUEST_SERVICE, REQUEST_METHOD,
			REQUEST_EMAIL, REQUEST_PWD);


}
