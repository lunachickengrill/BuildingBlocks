package com.components.xmlservlet.service;

import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.components.xmlservlet.api.ServiceResponse;
import com.components.xmlservlet.model.MailService;
import com.components.xmlservlet.repositories.MailServiceRepository;

@Service
public class RequestResponseServiceImpl implements RequestResponseService {
	
	private static final Logger logger = LoggerFactory.getLogger(RequestResponseService.class);

	private static final String REQUESTTYPE = "requestType";
	private static final String OPERATION = "CREATE";
	private static final String REQUESTID = "requestId";
	private static final String EMAILADDRESS = "emailAddress";
	private static final String PASSWORD = "password";

	private MailServiceRepository repo;
	private XmlConverter converter;

	@Autowired
	public RequestResponseServiceImpl(final MailServiceRepository repo, XmlConverter converter) {
		this.repo = repo;
		this.converter = converter;
	}

	@Override
	public String doTest(final String request) {
		// TODO
		return converter.toXmlTest();
	}

	@Override
	@Transactional
	public String doService(String xmlRequest) {
		// TODO Auto-generated method stub
		Map<String, String> xmlMap = converter.fromXmlRequest(xmlRequest);

		ServiceResponse serviceResponse = new ServiceResponse();

		// String requestId = new String();
		String emailAddress = new String();
		String password = new String();
		MailService mailService;

		if (!xmlMap.get(REQUESTTYPE).isEmpty() && xmlMap.get(REQUESTTYPE).equals(OPERATION)) {

			if (!(xmlMap.get(REQUESTID).isEmpty())) {
				// requestId = new String(xmlMap.get(REQUESTID));
				serviceResponse.setRequestId(xmlMap.get(REQUESTID));
			}

			if (!(xmlMap.get(EMAILADDRESS).isEmpty())) {
				emailAddress = xmlMap.get(EMAILADDRESS);
			}

			if (!(xmlMap.get(PASSWORD).isEmpty())) {
				password = xmlMap.get(PASSWORD);
			}

			mailService = new MailService(emailAddress, password);

			try {
				repo.saveAndFlush(mailService);
				serviceResponse.setStatus("SUCCESS");
				
			} catch (DataAccessException  ex) {
				serviceResponse.setStatus("FAIL");
				return converter.toXmlResponse(serviceResponse);
			}
		}

		logger.info(serviceResponse.toString());
		String xmlResponse = converter.toXmlResponse(serviceResponse);
		logger.info(xmlResponse);

		return xmlResponse;
	}

}
