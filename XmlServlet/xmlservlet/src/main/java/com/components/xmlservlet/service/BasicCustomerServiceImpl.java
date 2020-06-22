package com.components.xmlservlet.service;

import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.components.xmlservlet.api.ServiceRequest;
import com.components.xmlservlet.api.ServiceResponse;
import com.components.xmlservlet.model.Customer;
import com.components.xmlservlet.repositories.CustomerRepository;

@Service("basicCustomerService")
@Transactional
public class BasicCustomerServiceImpl implements BasicCustomerService {

	private static final Logger logger = LoggerFactory.getLogger(BasicCustomerService.class);

	private static final String EMAILADDRESS = "emailAddress";
	private static final String PASSWORD = "password";
	private CustomerRepository customerRepo;
	
	@Autowired
	public BasicCustomerServiceImpl(final CustomerRepository repo, XmlConverter converter) {
		this.customerRepo = repo;
	}

	@Override
	public ServiceResponse createCustomer(ServiceRequest req) {

		Customer customer = new Customer(req.getEmailAddress(), req.getPassword());
		customerRepo.saveAndFlush(customer);

		ServiceResponse resp = new ServiceResponse(req);
		resp.setStatus("SUCCESS");

		return resp;
	}

	@Override
	public ServiceResponse deleteCustomer(ServiceRequest req) {
		return new ServiceResponse(req);
		
	}

	@Override
	public ServiceResponse getCustomer(ServiceRequest req) {
		return new ServiceResponse(req);
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