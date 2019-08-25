package com.components.xmlservlet.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.components.xmlservlet.api.XmlServiceResponse;
import com.components.xmlservlet.api.XmlServiceRequest;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Service
public class XmlConverterImpl implements XmlConverter {

	private static final String REQUEST_ALIAS = "XmlServiceRequest";

	public XmlConverterImpl() {
	}

	@Override
	public Map<String, String> fromXmlRequest(String xml) {
		XStream xStream = new XStream(new DomDriver());
		xStream.alias(REQUEST_ALIAS, Map.class);
		Map<String, String> xmlMap;
		xStream.registerConverter(new MapEntryConverter());
		xmlMap = (Map<String, String>) xStream.fromXML(xml);
		return xmlMap;
	}

	@Override
	public String toXmlResponse(XmlServiceResponse serviceResponse) {
		XStream xStream = new XStream();
		xStream.processAnnotations(XmlServiceResponse.class);
		String xml = xStream.toXML(serviceResponse);
		return xml;
	}

	@Override
	public XmlServiceRequest toXmlRequest(String xml) {
		XStream xStream = new XStream();
		xStream.processAnnotations(XmlServiceRequest.class);
		XmlServiceRequest request = (XmlServiceRequest) xStream.fromXML(xml);

		return request;
	}

}
