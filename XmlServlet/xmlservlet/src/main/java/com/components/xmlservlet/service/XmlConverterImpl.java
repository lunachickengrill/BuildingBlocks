package com.components.xmlservlet.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.components.xmlservlet.api.ServiceMessage;
import com.components.xmlservlet.api.ServiceRequest;
import com.components.xmlservlet.api.ServiceResponse;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Service
public class XmlConverterImpl implements XmlConverter {

	private static final String REQUEST_ALIAS = "ServiceRequest";
	
	private Class<?>[] allowedClasses = new Class[] {ServiceMessage.class, ServiceRequest.class, ServiceResponse.class};

	public XmlConverterImpl() {
	}

	@Override
	public Map<String, String> fromXmlRequest(String xml) {
		XStream xStream = new XStream(new DomDriver());
		XStream.setupDefaultSecurity(xStream);
		xStream.allowTypes(allowedClasses);
		xStream.alias(REQUEST_ALIAS, Map.class);
		xStream.registerConverter(new MapEntryConverter());

		Map<String, String> xmlMap;
		xmlMap = (Map<String, String>) xStream.fromXML(xml);
		return xmlMap;

	}

	@Override
	public String toXmlResponse(ServiceResponse serviceResponse) {

		XStream xStream = new XStream();
		XStream.setupDefaultSecurity(xStream);
		xStream.allowTypes(allowedClasses);
		xStream.processAnnotations(ServiceResponse.class);

		String xml = xStream.toXML(serviceResponse);

		return xml;
	}

	@Override
	public ServiceRequest toServiceRequest(String xml) {

		XStream xStream = new XStream();
		XStream.setupDefaultSecurity(xStream);
		xStream.allowTypes(allowedClasses);
		xStream.processAnnotations(ServiceRequest.class);

		ServiceRequest request = (ServiceRequest) xStream.fromXML(xml);

		return request;
	}

	@Override
	public String toXmlRequest(ServiceRequest req) {
		XStream xStream = new XStream();
		XStream.setupDefaultSecurity(xStream);
		xStream.allowTypes(allowedClasses);
		xStream.processAnnotations(ServiceRequest.class);

		return xStream.toXML(req);
	}

}
