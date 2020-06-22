package com.components.xmlservlet.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.components.xmlservlet.api.ServiceResponse;
import com.components.xmlservlet.api.ServiceRequest;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Service
public class XmlConverterImpl implements XmlConverter {

	private static final String REQUEST_ALIAS = "ServiceRequest";

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
	public String toXmlResponse(ServiceResponse serviceResponse) {
		XStream xStream = new XStream();
		xStream.processAnnotations(ServiceResponse.class);
		String xml = xStream.toXML(serviceResponse);
		return xml;
	}

	@Override
	public ServiceRequest toRequest(String xml) {
		XStream xStream = new XStream();
		xStream.processAnnotations(ServiceRequest.class);
		ServiceRequest request = (ServiceRequest) xStream.fromXML(xml);

		return request;
	}

	@Override
	public String toXmlRequest(ServiceRequest req) {
		XStream xStream = new XStream();
		xStream.processAnnotations(ServiceRequest.class);
		return xStream.toXML(req);

	}

}
