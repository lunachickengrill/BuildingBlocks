package com.components.xmlservlet.service;

import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;

import com.components.xmlservlet.api.XmlServiceResponse;
import com.components.xmlservlet.model.MailService;
import com.components.xmlservlet.repositories.MailServiceRepository;

@Service
@Transactional
public class XmlDispatcherServiceImpl implements XmlDispatcherService {

	private static final Logger logger = LoggerFactory.getLogger(XmlDispatcherService.class);

	private static final String REQUESTMETHOD = "requestType";
	private static final String CREATE = "CREATE";
	private static final String DELETE = "DELETE";
	private static final String REQUESTID = "requestId";
	private static final String EMAILADDRESS = "emailAddress";
	private static final String PASSWORD = "password";
	private static final String STATUS_SUCCESS = "SUCCESS";
	private static final String STATUS_FAIL = "FAIL";
	private static final String STATUS_MISSINGPARAM = "MISSING PARAMETERS";
	private static final String STATUS_NOTFOUND = "NOT FOUND";

	private MailServiceRepository repo;
	private XmlConverter converter;

	@Autowired
	public XmlDispatcherServiceImpl(final MailServiceRepository repo, XmlConverter converter) {
		this.repo = repo;
		this.converter = converter;
	}


	@Override
	public String doService(String xmlRequest) {

		logger.info(xmlRequest);
		Map<String, String> xmlMap = converter.fromXmlRequest(xmlRequest);
		XmlServiceResponse serviceResponse = new XmlServiceResponse();

		if (checkParam(xmlMap, REQUESTID)) {
			serviceResponse.setRequestId(xmlMap.get(REQUESTID));
		}
		
		xmlMap.forEach((k,v)-> logger.info("xmlMap: " + k + " " + v));

		try {

			String method = xmlMap.get(REQUESTMETHOD);
			if (method.equals(CREATE)) {
				logger.info("creating email {}", xmlMap.get(EMAILADDRESS));
				if (!(checkMessageParams(xmlMap))) {
					serviceResponse.setStatus(STATUS_MISSINGPARAM);
				}
				repo.saveAndFlush(new MailService(xmlMap.get(EMAILADDRESS), xmlMap.get(PASSWORD)));
				serviceResponse.setRequestId(xmlMap.get(REQUESTID));
				serviceResponse.setStatus(STATUS_SUCCESS);
			}

			if (method.equals(DELETE)) {
				logger.info("deleting email {}", xmlMap.get(EMAILADDRESS));
				if (!(checkParam(xmlMap, EMAILADDRESS))) {
					serviceResponse.setStatus(STATUS_FAIL);

				}

				Optional<MailService> dbService = repo.findByEmailAddress(xmlMap.get(EMAILADDRESS));
				if (dbService.isPresent()) {
					repo.delete(dbService.get());
					serviceResponse.setStatus(STATUS_SUCCESS);
					serviceResponse.setRequestId(xmlMap.get(REQUESTID));
				} else {
					serviceResponse.setStatus(STATUS_NOTFOUND);
				}
			}
		} catch (UnexpectedRollbackException | DataAccessException ex) {
			serviceResponse.setStatus(STATUS_FAIL);
			
		}

		String xmlResponse = converter.toXmlResponse(serviceResponse);

		logger.info(xmlResponse);
		return xmlResponse;
	}

	private boolean checkParam(Map<String, String> xmlMap, String param) {
		boolean isValid = false;

		if (xmlMap.get(param) != null)
			isValid = true;

		return isValid;
	}

	
	private boolean checkMessageParams(Map<String, String> xmlMap) {
		boolean isValid = true;

		if (xmlMap.get(EMAILADDRESS) == null || xmlMap.get(PASSWORD) == null) {
			isValid = false;
		}

		return isValid;
	}
}