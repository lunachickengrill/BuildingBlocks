package com.components.xmlservlet.service;

import java.util.Map;

import com.components.xmlservlet.api.ServiceMessage;
import com.components.xmlservlet.api.XmlServiceResponse;
import com.components.xmlservlet.api.XmlServiceRequest;

public interface XmlConverter {

	public Map<String, String> fromXmlRequest(final String xml);

	public String toXmlResponse(XmlServiceResponse serviceResponse);

	public XmlServiceRequest toXmlRequest(final String xml);

//	public String toXmlTest();
//	
//	public Map<String, String> fromXmlToMap(final String xml);

}
