package com.components.xmlservlet.service;

import javax.transaction.Transactional;
import javax.validation.ValidationException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Validator;

import com.components.xmlservlet.api.ServiceRequest;
import com.components.xmlservlet.api.ServiceResponse;
import com.components.xmlservlet.exception.XmlServiceException;
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
//		validateBean(req);
		
		logger.info("createCustomer: " + req.getRequestId());

		Customer customer = Customer.fromServiceRequest(req);
		customerRepo.saveAndFlush(customer);

		ServiceResponse resp = new ServiceResponse(req);

		return resp;
	}

	@Override
	public ServiceResponse deleteCustomer(ServiceRequest req) {
//		validateBean(req);
		logger.info("deleteCustomer: " + req.getRequestId());
		return new ServiceResponse(req);
		
	}

	@Override
	public ServiceResponse getCustomer(ServiceRequest req) {
//		validateBean(req);
		logger.info("getCustomer: " + req.getRequestId());
		return new ServiceResponse(req);
	}
	
	

}