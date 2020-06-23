package com.components.xmlservlet.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.MethodInvoker;
import org.springframework.validation.DataBinder;

import com.components.xmlservlet.api.ServiceMessage;
import com.components.xmlservlet.api.ServiceRequest;
import com.components.xmlservlet.api.ServiceResponse;
import com.components.xmlservlet.exception.XmlServiceException;

@Service
public class DispatcherServiceImpl implements DispatcherService {

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
			Map<String, String> elementMap = converter.fromXmlRequest(xml);

			String requestService = elementMap.get("requestService");
			String requestMethod = elementMap.get("requestMethod");

			if (requestService == null || requestMethod == null) {
				throw new RuntimeException("message does not contain service or method name");
			}

			ApplicationService service = lookupService(requestService);
			if (service == null) {
				throw new XmlServiceException("Service not found with name " + requestService);
			}

			Method method = lookupMethod(service, requestMethod);

			ServiceMessage serviceMessage = createServiceRequest(method);

			serviceMessage = bindParameter(serviceMessage, elementMap);

			resp = invoke(service, method, serviceMessage);
			return converter.toXmlResponse(resp);
		} catch (XmlServiceException ex) {
			resp.setStatus(ex.getMessage());
			return converter.toXmlResponse(resp);
		}
	}

	private ServiceMessage bindParameter(ServiceMessage message, Map<String, String> parameter) {
		DataBinder dataBinder = new DataBinder(message);
		MutablePropertyValues mpv = new MutablePropertyValues();
		mpv.addPropertyValues(parameter);
		dataBinder.bind(mpv);
		return message;

	}

	private ServiceMessage createServiceRequest(Method method) {
		Class<?> paramType = method.getParameterTypes()[0];
		Assert.isTrue(ServiceMessage.class.isAssignableFrom(paramType), "Expected subclass of ServiceMessage");

		try {
			return (ServiceMessage) paramType.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | NoSuchMethodException | InvocationTargetException
				| IllegalAccessException ex) {
			throw new XmlServiceException("Cannot create ServiceMessage");
		}

	}

	private ApplicationService lookupService(final String serviceName) {

		Boolean serviceExists = ctx.containsBean(serviceName);
		if (!serviceExists) {
			throw new XmlServiceException("Cannot lookup service with name " + serviceName);
		}

		return ctx.getBean(serviceName, ApplicationService.class);

	}

	private Method lookupMethod(ApplicationService service, String methodName) {

		List<Method> methods = Arrays.asList(service.getClass().getDeclaredMethods());
		Optional<Method> method = methods.stream().filter(m -> m.getName().equals(methodName)).findFirst();
		if (method != null) {
			return method.get();
		} else {
			throw new XmlServiceException("Cannot lookup method with name " + methodName);
		}
	}

	private ServiceResponse invoke(ApplicationService service, Method method, ServiceMessage serviceMessage) {

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
		} catch (InvocationTargetException | IllegalAccessException ex) {
			resp.setStatus("ERROR");
			throw new XmlServiceException(ex.getMessage());
		}

	}

}
