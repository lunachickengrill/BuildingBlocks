package com.components.xmlservlet;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.components.xmlservlet.api.ServiceRequest;
import com.components.xmlservlet.api.ServiceResponse;
import com.components.xmlservlet.service.XmlConverter;
import com.thoughtworks.xstream.XStream;

public class XmlConverterTest extends AbstractBaseTest {

	@Autowired
	private XmlConverter converter;

	@Test
	public void toMapfromXml() {
		Map<String, String> xmlMap = converter.fromXmlRequest(XML_REQUEST);

		assertTrue(!(xmlMap.entrySet().isEmpty()));

		xmlMap.forEach((k,v)->System.out.println("key " + k + " value: " + v));
	}

	@Test
	public void toXmlServiceResponse() {
		ServiceResponse response = new ServiceResponse(SERVICEREQUEST);
		String xml = converter.toXmlResponse(response);
		assertTrue(!(xml.isEmpty()));
		assertTrue(xml.contains("ABC001"));

	}

	@Test
	public void toXmlMap() {
		Map<String, String> xmlMap = converter.fromXmlRequest(XML_REQUEST);

		for (Map.Entry<String, String> entry : xmlMap.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}
	
	@Test
	public void toXmlServiceRequest() {
		String request = converter.toXmlRequest(new ServiceRequest("123", "basicCustomerService", "createCustomer", "emailAdress", "password"));
		assertNotNull(request);
		
		System.out.println(request);
	}

	

}
