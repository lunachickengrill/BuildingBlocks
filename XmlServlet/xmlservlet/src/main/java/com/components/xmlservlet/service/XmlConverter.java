package com.components.xmlservlet.service;

import java.util.Map;

import com.components.xmlservlet.api.ServiceRequest;
import com.components.xmlservlet.api.ServiceResponse;

public interface XmlConverter {

	public Map<String, String> fromXmlRequest(final String xml);

	public String toXmlResponse(ServiceResponse serviceResponse);

	public ServiceRequest toServiceRequest(String xml);

	public String toXmlRequest(ServiceRequest req);

}
