package com.components.xmlservlet;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.components.xmlservlet.api.ServiceResponse;
import com.components.xmlservlet.api.XmlServiceRequest;
import com.components.xmlservlet.service.XmlConverter;

public class XmlConverterTest extends AbstractBaseTest {

	@Autowired
	private XmlConverter converter;

	@Test
	public void testToXml() {
		String xml = converter.toXmlTest();
		assertNotNull(xml);

		System.out.println(xml);
	}

	@Ignore
	@Test
	public void fromXmlRequest() {
		XmlServiceRequest request = converter.fromXmlRequest(XML_REQUEST);

		assertTrue(request.getEmailAddress().equals("abc@def.com") && request.getType().equals("CREATE")
				&& request.getRequestId().equals("ABC001"));
	}

	@Ignore
	@Test
	public void toXmlServiceResponse() {
		ServiceResponse response = new ServiceResponse(SERVICEREQUEST);
		String xml = converter.toXmlResponse(response);
		assertTrue(!(xml.isEmpty()));
		assertTrue(xml.contains("ABC001"));

	}

}
