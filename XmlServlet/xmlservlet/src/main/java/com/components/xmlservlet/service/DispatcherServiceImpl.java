package com.components.xmlservlet.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.MethodInvoker;
import org.springframework.util.StringUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;

import com.components.xmlservlet.api.ServiceMessage;
import com.components.xmlservlet.api.ServiceRequest;
import com.components.xmlservlet.api.ServiceResponse;
import com.components.xmlservlet.exception.BeanValidationException;
import com.components.xmlservlet.exception.XmlServiceException;

@Service
public class DispatcherServiceImpl implements DispatcherService {
	
	Logger logger = LoggerFactory.getLogger(DispatcherServiceImpl.class);

	@Autowired
	private Validator validator;

	@Autowired
	private XmlConverter converter;

	@Autowired
	private ApplicationContext ctx;

	public DispatcherServiceImpl() {
	}

	@Override
	public String dispatch(String xml) {
		ServiceRequest req = converter.toServiceRequest(xml);
		ServiceResponse resp = new ServiceResponse(req);

		try {
			logger.info("processing request");
			Map<String, String> elementMap = converter.fromXmlRequest(xml);

			String requestService = elementMap.get("requestService");
			String requestMethod = elementMap.get("requestMethod");

			if (requestService == null || requestMethod == null) {
				throw new XmlServiceException("message does not contain service or method name");
			}
			
			// Lookup the applicationService
			ApplicationService service = lookupService(requestService);
			
			// Lookup the service method
			Method method = lookupMethod(service, requestMethod);
			
			// Create a service request with method
			ServiceMessage serviceMessage = createServiceRequest(method);
			
			// Binding the request params to the serviceMessage bean
			serviceMessage = bindParameter(serviceMessage, elementMap);
			
			// Validate the created bean
			validateBean(serviceMessage);

			// invoke the actual service method		
			resp = invoke(service, method, serviceMessage);
			
			return converter.toXmlResponse(resp);
		} catch (XmlServiceException  ex) {
			logger.info("Exception message: " + ex.getMessage());
			resp.setStatus("ERROR");
			resp.setStatusMessage(ex.getMessage());
			return converter.toXmlResponse(resp);
		}
	}

	private ServiceMessage bindParameter(ServiceMessage message, Map<String, String> parameter) {
		
		logger.info("binding parameters to ServiceMessage");
		
		DataBinder dataBinder = new DataBinder(message);
		MutablePropertyValues mpv = new MutablePropertyValues();
		mpv.addPropertyValues(parameter);
		dataBinder.bind(mpv);
		return message;

	}

	private ServiceMessage createServiceRequest(Method method) throws XmlServiceException {
		
		logger.info("create ServiceMessage object with method {}", method.getName());
		Class<?> paramType = method.getParameterTypes()[0];
		Assert.isTrue(ServiceMessage.class.isAssignableFrom(paramType), "Expected subclass of ServiceMessage");

		try {
			return (ServiceMessage) paramType.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | NoSuchMethodException | InvocationTargetException
				| IllegalAccessException ex) {
			throw new XmlServiceException("Cannot create ServiceMessage");
		}
	}

	private ApplicationService lookupService(final String serviceName) throws XmlServiceException {

		logger.info("lookup service with name {} in spring context", serviceName);
		Boolean serviceExists = ctx.containsBean(serviceName);
		
		if (!serviceExists) {
			throw new XmlServiceException("Cannot lookup service with name " + serviceName);
		}

		return ctx.getBean(serviceName, ApplicationService.class);
	}

	private Method lookupMethod(ApplicationService service, String methodName) throws XmlServiceException {

		List<Method> methods = Arrays.asList(service.getClass().getDeclaredMethods());
		Optional<Method> method = methods.stream().filter(m -> m.getName().equals(methodName)).findFirst();
		logger.info("lookup method {} on service {}", methodName, service.getClass().getName());
		if (method != null) {
			return method.get();
		} else {
			throw new XmlServiceException("Cannot lookup method with name " + methodName);
		}
	}

	private ServiceResponse invoke(ApplicationService service, Method method, ServiceMessage serviceMessage)
			throws XmlServiceException {
		
		logger.info("invoking service {} with method {} for message with requestId {}", service.getClass().getSimpleName(),method.getName(),serviceMessage.getRequestId());

		MethodInvoker invoker = new MethodInvoker();
		invoker.setTargetClass(service.getClass());
		invoker.setTargetObject(service);
		invoker.setTargetMethod(method.getName());
		invoker.setArguments(serviceMessage);

		ServiceResponse resp = new ServiceResponse(serviceMessage);
		try {
			invoker.prepare();

		} catch (ClassNotFoundException | NoSuchMethodException ex) {
			resp.setStatus("ERROR");
			throw new XmlServiceException(ex.getMessage());
		}

		try {
			return (ServiceResponse) invoker.invoke();
		} catch (InvocationTargetException | IllegalAccessException | XmlServiceException ex) {
			resp.setStatus("ERROR");
			throw new XmlServiceException(ex.getMessage());
		}

	}

	public void validateBean(Object obj) {
		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(obj, obj.getClass().getSimpleName());
		validator.validate(obj, bindingResult);

		if (bindingResult.hasErrors()) {
			logger.info("validation object {}", obj.toString());
			throw new BeanValidationException("Bean validation error");
		}

	}

}
