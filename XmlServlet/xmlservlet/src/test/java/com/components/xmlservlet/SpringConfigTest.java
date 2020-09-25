package com.components.xmlservlet;


import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;


public class SpringConfigTest extends AbstractBaseTest {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private ApplicationContext context;
	
	@Test
	public void loadContext() {
		
	}
	
	
	@Test
	public void testEnvironment() {
		
		List<String> profiles = Arrays.asList(env.getActiveProfiles());
		assertTrue("No profiles active",profiles.size()>0);
		
		assertTrue("Property does not exist",env.containsProperty("db.driver"));
		
		

	}

}
