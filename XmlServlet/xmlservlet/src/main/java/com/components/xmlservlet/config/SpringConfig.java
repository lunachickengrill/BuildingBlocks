package com.components.xmlservlet.config;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ PersistanceConfig.class })
@ServletComponentScan(basePackages = { "com.components.xmlservlet.web" })
public class SpringConfig {
	

}
