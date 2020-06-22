package com.components.xmlservlet;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.components.xmlservlet.api.ServiceResponse;
import com.components.xmlservlet.service.XmlConverter;

public class XmlConverterTest extends AbstractBaseTest {

	@Autowired
	private XmlConverter converter;

	@Test
	public void toMapfromXml() {
		Map<String, String> xmlMap = converter.fromXmlRequest(XML_REQUEST);

		assertTrue(!(xmlMap.entrySet().isEmpty()));
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

}
