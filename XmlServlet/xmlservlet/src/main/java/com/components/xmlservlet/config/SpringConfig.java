package com.components.xmlservlet.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.components.xmlservlet.api.ServiceRequest;
import com.components.xmlservlet.api.ServiceResponse;
import com.components.xmlservlet.service.DispatcherService;
import com.components.xmlservlet.service.MapEntryConverter;
import com.components.xmlservlet.web.XmlServlet;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Configuration
@Import({ PersistanceConfig.class })
@ServletComponentScan(basePackages = { "com.components.xmlservlet.web" })
public class SpringConfig {
	
	private static final String REQUEST_ALIAS = "ServiceRequest";
	
//	Instead of defining the beans for the servlet, the servlet itself is annotated with @WebServlet	
//    @Bean
//    public XmlServlet tmngxXmlMessageServlet() {
//        return new XmlServlet(dispatcherService);
//    }
	
//	@Bean
//	public ServletRegistrationBean<XmlServlet> xmlServletRegistrationBean(){
//		ServletRegistrationBean<XmlServlet> s = new ServletRegistrationBean<XmlServlet>();
//		s.setLoadOnStartup(12);
//		return s;
//	}
	
//	@Bean("xStreamDom")
//	public XStream getXstreamForDOM() {
//		XStream xStream = new XStream(new DomDriver());
//		XStream.setupDefaultSecurity(xStream);
//		xStream.allowTypesByWildcard(new String[] {"com.components.xmlservlet.api.**"});
//		xStream.alias(REQUEST_ALIAS, Map.class);
//		xStream.registerConverter(new MapEntryConverter());
//		return xStream;
//	}
//	
//	@Bean("xStreamServiceResponse")
//	public XStream getXStreamForServiceResponse() {
//		XStream xStream = new XStream();
//		XStream.setupDefaultSecurity(xStream);
//		xStream.allowTypesByWildcard(new String[] {"com.components.xmlservlet.api.**"});
//		xStream.processAnnotations(ServiceResponse.class);
//		return xStream;		
//	}
//	
//	@Bean("xStreamServiceRequest")
//	public XStream getXStreamForServiceRequest() {
//		XStream xStream = new XStream();
//		XStream.setupDefaultSecurity(xStream);
//		xStream.allowTypesByWildcard(new String[] {"com.components.xmlservlet.api.**"});
//		xStream.processAnnotations(ServiceRequest.class);
//		return xStream;
//	}

}
