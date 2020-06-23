package com.components.xmlservlet.web;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.util.WebUtils;

import com.components.xmlservlet.service.DispatcherService;
import com.components.xmlservlet.service.XmlConverter;

@WebServlet(urlPatterns = "/service")
public class XmlServlet extends FrameworkServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 752827566880733133L;

	Logger logger = LoggerFactory.getLogger(XmlServlet.class);

	private DispatcherService dispatcherService;
	
	@Autowired
	private XmlConverter converter;

	@Autowired
	public XmlServlet(final DispatcherService dispatcherService) {
		this.dispatcherService = dispatcherService;
	}

	@Override
	protected void initFrameworkServlet() throws ServletException {
		super.initFrameworkServlet();
	}

	@Override
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Assert.isTrue("POST".equals(request.getMethod()), "only POST requests are handled");

		String xmlRequest = retrieveTmngxXmlRequest(request);
		logger.debug("processing XML message from {}: {}", request.getRemoteHost(), xmlRequest);
		
		System.out.println(xmlRequest);

		String xmlResponse = dispatcherService.dispatch(xmlRequest);

		// Send back XML response
		response.setContentType(MimeTypeUtils.APPLICATION_XML_VALUE);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(xmlResponse);
		response.getWriter().flush();

		logger.debug("sending back XML reponse {}", xmlResponse);

	}

	private String retrieveTmngxXmlRequest(HttpServletRequest request) throws IOException {
		String xmlRequest;
		MimeType mimeType = MimeType.valueOf(request.getContentType());

		if (MimeTypeUtils.APPLICATION_XML.isCompatibleWith(mimeType)
				|| MimeTypeUtils.TEXT_XML.isCompatibleWith(mimeType)) {

			String characterEncoding = StringUtils.defaultString(request.getCharacterEncoding(),
					WebUtils.DEFAULT_CHARACTER_ENCODING);
			xmlRequest = StreamUtils.copyToString(request.getInputStream(), Charset.forName(characterEncoding));
		} else if (mimeType.isCompatibleWith(MediaType.APPLICATION_FORM_URLENCODED)) {
			xmlRequest = request.getParameter("tmngxXmlRequest");
			if (xmlRequest == null) {
				throw new IllegalArgumentException(
						"need parameter 'serviceRequest' but got " + request.getParameterMap().keySet());
			}
		} else {
			throw new IllegalArgumentException("unsupported mimetype " + mimeType + " (from" + request.getContentType()
					+ ")." + "Use " + MimeTypeUtils.APPLICATION_XML_VALUE + ", " + MimeTypeUtils.TEXT_XML_VALUE + " or "
					+ MediaType.APPLICATION_FORM_URLENCODED_VALUE + ".");
		}
		Assert.isTrue(!xmlRequest.isEmpty(), "xmlRequest is empty");

		return xmlRequest;
	}

}
