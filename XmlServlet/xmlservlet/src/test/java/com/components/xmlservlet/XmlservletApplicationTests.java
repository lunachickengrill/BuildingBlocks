package com.components.xmlservlet;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.components.xmlservlet.api.ServiceRequest;
import com.components.xmlservlet.repositories.CustomerRepository;
import com.components.xmlservlet.service.BasicCustomerService;
import com.components.xmlservlet.service.BasicCustomerServiceImpl;
import com.components.xmlservlet.service.DispatcherServiceImpl;
import com.components.xmlservlet.service.XmlConverter;

public class XmlservletApplicationTests extends AbstractBaseTest {

	@Autowired
	private CustomerRepository repo;

	@Autowired
	private DispatcherServiceImpl dispatcherService;

	@Autowired
	private XmlConverter converter;

	@Autowired
	private BasicCustomerServiceImpl customerService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void checkSetup() {

		Long count = repo.count();
		assertTrue(count == 3);

	}

	@Test
	public void testDispatcher() {
		String reqXml = converter.toXmlRequest(SERVICEREQUEST);
		assertTrue("Request does not contain " + REQUEST_SERVICE, reqXml.contains(REQUEST_SERVICE));
		assertTrue("Request does not contain " + REQUEST_METHOD, reqXml.contains(REQUEST_METHOD));
		System.out.println(reqXml);

		String respXml = dispatcherService.dispatch(reqXml);

		System.out.println(respXml);
	}

	@Test
	public void testDispatcherWithUnknownService() {
		ServiceRequest req = new ServiceRequest("12345", "diesenServiceGibtEsNicht", "dieseMethodeAuchNicht", "123456",
				"First", "Last", "first.last@kapschcs.at", "asdf");
		String xmlReq = converter.toXmlRequest(req);

		String respXml = dispatcherService.dispatch(xmlReq);
		assertTrue(respXml.contains("Cannot lookup service with name"));

		System.out.println(respXml);
	}
	
	@Test
	public void testDispatcherWitBeanValidationError() {
		ServiceRequest req = new ServiceRequest("12345", "basicCustomerService", "createCustomer", null,
				"First", "Last", "first.last@kapschcs.at", "asdf");
		
		String xmlReq = converter.toXmlRequest(req);
		String respXml = dispatcherService.dispatch(xmlReq);
		
		assertTrue(respXml.contains("Bean validation error"));

	}

	
	@Test
	public void reflectMethodsOfService() {
		for (Method m : customerService.getClass().getDeclaredMethods()) {
			System.out.println(m.getName());
		}
	}

}
