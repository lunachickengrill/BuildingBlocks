package com.components.xmlservlet.service;

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

	private CustomerRepository customerRepo;
	
	@Autowired
	public BasicCustomerServiceImpl(final CustomerRepository repo, XmlConverter converter) {
		this.customerRepo = repo;
	}

	@Override
	public ServiceResponse createCustomer(ServiceRequest req) {
		
		logger.info("createCustomer: " + req.getRequestId());

		Customer customer = new Customer(req.getEmailAddress(), req.getPassword());
		customerRepo.saveAndFlush(customer);

		ServiceResponse resp = new ServiceResponse(req);

		return resp;
	}

	@Override
	public ServiceResponse deleteCustomer(ServiceRequest req) {
		logger.info("deleteCustomer: " + req.getRequestId());
		return new ServiceResponse(req);
		
	}

	@Override
	public ServiceResponse getCustomer(ServiceRequest req) {
		logger.info("getCustomer: " + req.getRequestId());
		return new ServiceResponse(req);
	}

}