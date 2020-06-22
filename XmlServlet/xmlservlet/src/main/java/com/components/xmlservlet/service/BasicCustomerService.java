package com.components.xmlservlet.service;

import com.components.xmlservlet.api.ServiceRequest;
import com.components.xmlservlet.api.ServiceResponse;

public interface BasicCustomerService extends ApplicationService {

	public ServiceResponse createCustomer(ServiceRequest req);

	public ServiceResponse deleteCustomer(ServiceRequest req);

	public ServiceResponse getCustomer(ServiceRequest req);

}