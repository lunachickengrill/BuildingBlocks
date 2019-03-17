package com.components.xmlservlet.service;

import com.components.xmlservlet.api.ServiceRequest;
import com.components.xmlservlet.api.ServiceResponse;
import com.components.xmlservlet.api.XmlServiceRequest;

public interface XmlConverter {

	public XmlServiceRequest fromXmlRequest(final String xml);

	public String toXmlResponse(ServiceResponse serviceResponse);
	
	public String toXmlTest();

}
