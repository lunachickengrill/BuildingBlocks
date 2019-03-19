package com.components.xmlservlet.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.components.xmlservlet.api.ServiceResponse;
import com.components.xmlservlet.api.XmlServiceRequest;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Service
public class XmlConverterImpl implements XmlConverter {

	private static final String REQUEST_ALIAS = "XmlServiceRequest";
	private static final String RESPONSE_ALIAS = "XmlServiceResponse";
	private ServiceResponse testResponse = new ServiceResponse();

	public XmlConverterImpl() {
	}

//	@Override
//	public XmlServiceRequest fromXmlRequest(String xml) {
//		XStream xStream = new XStream();
//		xStream.processAnnotations(XmlServiceRequest.class);
//		return (XmlServiceRequest) xStream.fromXML(xml);
//	}

//	@Override
//	public String toXmlResponse(ServiceResponse serviceResponse) {
//		XStream xStream = new XStream();
//		xStream.processAnnotations(ServiceResponse.class);
//		return xStream.toXML(serviceResponse);
//	}
	
	@Override
	public Map<String, String> fromXmlRequest(String xml) {
		XStream xStream = new XStream(new DomDriver());
		xStream.alias(REQUEST_ALIAS, Map.class);
		Map<String,String> xmlMap;
		xStream.registerConverter(new MapEntryConverter());
		xmlMap = (Map<String, String>) xStream.fromXML(xml);
		return xmlMap;
	}

	@Override
	public String toXmlResponse(ServiceResponse serviceResponse) {
		XStream xStream = new XStream();
		xStream.processAnnotations(ServiceResponse.class);
		String xml = xStream.toXML(serviceResponse);
		return xml;
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


	@Override
	public Map<String, String> fromXmlToMap(String xml) {
		XStream xStream = new XStream(new DomDriver());
		xStream.alias(REQUEST_ALIAS, Map.class);
		Map<String, String> xmlMap;
		xStream.registerConverter(new MapEntryConverter());
		xmlMap = (Map<String, String>) xStream.fromXML(xml);

		return xmlMap;
	}

}
