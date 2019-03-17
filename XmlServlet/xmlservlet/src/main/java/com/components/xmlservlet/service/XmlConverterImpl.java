package com.components.xmlservlet.service;

import org.springframework.stereotype.Service;

import com.components.xmlservlet.api.ServiceResponse;
import com.components.xmlservlet.api.XmlServiceRequest;
import com.thoughtworks.xstream.XStream;

@Service
public class XmlConverterImpl implements XmlConverter {

	private static final String REQUEST_ALIAS = "XmlServiceRequest";
	private static final String RESPONSE_ALIAS = "XmlServiceResponse";
	private ServiceResponse testResponse = new ServiceResponse();

	public XmlConverterImpl() {
	}

	@Override
	public XmlServiceRequest fromXmlRequest(String xml) {
		XStream xStream = new XStream();
		xStream.processAnnotations(XmlServiceRequest.class);
		return (XmlServiceRequest) xStream.fromXML(xml);
	}

	@Override
	public String toXmlResponse(ServiceResponse serviceResponse) {
		XStream xStream = new XStream();
		xStream.processAnnotations(ServiceResponse.class);
		return xStream.toXML(serviceResponse);
	}

	@Override
	public String toXmlTest() {
		XStream xStream = new XStream();
		xStream.alias(RESPONSE_ALIAS, ServiceResponse.class);

		testResponse.setRequestId("0000001");
		testResponse.setStatus("TEST SUCCESSFULL");
		String xml = xStream.toXML(testResponse);
		return xml;
	}

}
