package com.components.xmlservlet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.components.xmlservlet.repositories.MailServiceRepository;

@Service
public class RequestResponseServiceImpl implements RequestResponseService {

	private MailServiceRepository repo;
	private XmlConverter converter;

	@Autowired
	public RequestResponseServiceImpl(final MailServiceRepository repo, XmlConverter converter) {
		this.repo = repo;
		this.converter=converter;
	}

	/* (non-Javadoc)
	 * @see com.components.xmlservlet.service.RequestResponseService#doTest(com.components.xmlservlet.service.ServiceRequest)
	 */
	@Override
	public String doTest(final String request) {
		//TODO
		return converter.toXmlTest();
	}

	@Override
	public String dispatch(String xmlRequest) {
		// TODO Auto-generated method stub
		
		
		
		return null;
	}
	
	
	
	

}
