package com.components.xmlservlet;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.components.xmlservlet.api.XmlServiceRequest;
import com.components.xmlservlet.repositories.MailServiceRepository;

public class XmlservletApplicationTests extends AbstractBaseTest {

	@Autowired
	private MailServiceRepository repo;

	@Test
	public void contextLoads() {
	}

	@Test
	public void checkSetup() {

		Long count = repo.count();
		assertTrue(count > 0);

	}
	


}
