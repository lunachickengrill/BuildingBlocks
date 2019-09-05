package com.components.xmlservlet.config;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.components.xmlservlet.web.XmlServlet;

@Configuration
@Import({ PersistanceConfig.class })
@ServletComponentScan(basePackages = { "com.components.xmlservlet.web" })
public class SpringConfig {
	
//    @Bean
//    public XmlServlet tmngxXmlMessageServlet() {
//        return new XmlServlet(service, converter);
//    }
//	
//	@Bean
//	public ServletRegistrationBean<XmlServlet> xmlServletRegistrationBean(){
//		ServletRegistrationBean<XmlServlet> s = new ServletRegistrationBean<XmlServlet>();
//		s.setLoadOnStartup(12);
//		return s;
//	}
	

}
