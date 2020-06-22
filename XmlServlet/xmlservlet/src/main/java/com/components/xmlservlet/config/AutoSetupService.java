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
	private void createData() {

		List<Customer> customers = new ArrayList<>();

		customers.add(new Customer(EMAIL_1, EMAIL_1_PWD));
		customers.add(new Customer(EMAIL_2, EMAIL_2_PWD));
		customers.add(new Customer(EMAIL_3, EMAIL_3_PWD));

		repo.saveAll(customers);

	}

}
