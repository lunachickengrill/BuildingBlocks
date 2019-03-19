package com.components.xmlservlet.service;

import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;

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

		logger.info(xmlRequest);
		Map<String, String> xmlMap = converter.fromXmlRequest(xmlRequest);
		ServiceResponse serviceResponse = new ServiceResponse();

		if ((xmlMap.get(REQUESTTYPE).isEmpty() || (!xmlMap.get(REQUESTTYPE).equals(OPERATION)))
				|| xmlMap.get(EMAILADDRESS).isEmpty() || xmlMap.get(PASSWORD).isEmpty()
				|| xmlMap.get(REQUESTID).isEmpty()) {
			serviceResponse.setStatus("MISSING PARAM");
			serviceResponse.setRequestId(xmlMap.get(REQUESTID));
		} else {
			serviceResponse.setRequestId(xmlMap.get(REQUESTID));
		}

		try {
			repo.saveAndFlush(new MailService(xmlMap.get(EMAILADDRESS), xmlMap.get(PASSWORD)));
			serviceResponse.setStatus("SUCCESS");
		} catch (DataIntegrityViolationException  ex) {
			serviceResponse.setStatus(ex.getCause().toString());
		}

		String xmlResponse = converter.toXmlResponse(serviceResponse);

		logger.info(xmlResponse);
		return xmlResponse;
	}
}
