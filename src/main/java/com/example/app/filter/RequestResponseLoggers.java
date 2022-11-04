package com.example.app.filter;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.app.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Order(1)
public class RequestResponseLoggers implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(RequestResponseLoggers.class);
	
	@Autowired
	private ObjectMapper mapper;
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		logger.info("Request URI: {}", request.getRequestURI());
		logger.info("Request Method: {}", request.getMethod());
		
		// masking birthday
		String requestData = new String(IOUtils.toByteArray(request.getInputStream()));
		User user = mapper.readValue(requestData, User.class);
		user.setDob("********");
		requestData = mapper.writeValueAsString(user);
		
		logger.info("Request Body: {}", requestData);
		
		chain.doFilter(request, response);
	}

}
