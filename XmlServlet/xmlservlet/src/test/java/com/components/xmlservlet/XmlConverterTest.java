package com.components.xmlservlet;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.components.xmlservlet.api.ServiceRequest;
import com.components.xmlservlet.api.ServiceResponse;
import com.components.xmlservlet.service.XmlConverter;

public class XmlConverterTest extends AbstractBaseTest {

	@Autowired
	private XmlConverter converter;
	
	@Autowired
	ApplicationContext context;
	

	@Test
	public void testToMapfromXml() {
		Map<String, String> xmlMap = converter.fromXmlRequest(XML_REQUEST);
		assertTrue(!(xmlMap.entrySet().isEmpty()));
		assertTrue(xmlMap.containsValue(REQUEST_SERVICE));
	}

	@Test
	public void testResponseToXml() {
		ServiceResponse response = new ServiceResponse(SERVICEREQUEST);
		String xml = converter.toXmlResponse(response);
		assertTrue(!(xml.isEmpty()));
		assertTrue(xml.contains(REQUEST_ID));
	}

	@Test
	public void testToRequestXml() {
		String request = converter.toXmlRequest(new ServiceRequest("123", "basicCustomerService", "createCustomer", "emailAdress", "password"));
		assertNotNull(request);		
		assertTrue(request.contains(REQUEST_SERVICE));
	}
	
	@Test
	public void testXmlToRequest() {		
		ServiceRequest req = converter.toServiceRequest(XML_REQUEST);
		assertNotNull(req);
		assertTrue(req.getRequestService().equals(REQUEST_SERVICE));
	}
	

}
