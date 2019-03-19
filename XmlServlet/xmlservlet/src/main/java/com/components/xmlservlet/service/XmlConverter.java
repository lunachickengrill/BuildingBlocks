package com.components.xmlservlet.service;

import java.util.Map;

import com.components.xmlservlet.api.ServiceRequest;
import com.components.xmlservlet.api.ServiceResponse;
import com.components.xmlservlet.api.XmlServiceRequest;

public interface XmlConverter {

	public Map<String, String> fromXmlRequest(final String xml);

	public String toXmlResponse(ServiceResponse serviceResponse);
	
	public String toXmlTest();
	
	public Map<String, String> fromXmlToMap(final String xml);

}
