package com.components.xmlservlet.config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.components.xmlservlet.model.Customer;
import com.components.xmlservlet.repositories.CustomerRepository;

@Service
public class AutoSetupService {

	public static final String CUSTID_1 = "123";
	public static final String CUSTID_2 = "456";
	public static final String CUSTID_3 = "789";

	public static final String FIRST_1 = "First 1";
	public static final String FIRST_2 = "First 2";
	public static final String FIRST_3 = "First 3";

	public static final String LAST_1 = "Last 1";
	public static final String LAST_2 = "Last 3";
	public static final String LAST_3 = "Last 3";

	public static final String EMAIL_1 = "emailOne@gmail.com";
	public static final String EMAIL_2 = "emailTwo@gmail.com";
	public static final String EMAIL_3 = "emailThree@gmail.com";

	public static final String EMAIL_1_PWD = "123";
	public static final String EMAIL_2_PWD = "456";
	public static final String EMAIL_3_PWD = "789";

	private CustomerRepository repo;

	@Autowired
	public AutoSetupService(CustomerRepository repo) {
		this.repo = repo;
	}

	@PostConstruct
	private void createDummyData() {

		List<Customer> customers = new ArrayList<>();

		Customer cust1 = new Customer(CUSTID_1);
		cust1.setFirstName(FIRST_1);
		cust1.setLastName(LAST_1);
		cust1.setEmailAddress(EMAIL_1);
		cust1.setPassword(EMAIL_1_PWD);

		Customer cust2 = new Customer(CUSTID_2);
		cust2.setFirstName(FIRST_2);
		cust2.setLastName(LAST_2);
		cust2.setEmailAddress(EMAIL_2);
		cust2.setPassword(EMAIL_2_PWD);

		Customer cust3 = new Customer(CUSTID_3);
		cust3.setFirstName(FIRST_3);
		cust3.setLastName(LAST_3);
		cust3.setEmailAddress(EMAIL_3);
		cust3.setPassword(EMAIL_3_PWD);

		customers.add(cust1);
		customers.add(cust2);
		customers.add(cust3);

		repo.saveAll(customers);

	}

}
