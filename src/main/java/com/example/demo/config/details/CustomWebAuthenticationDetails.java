package com.example.demo.config.details;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class CustomWebAuthenticationDetails extends WebAuthenticationDetails{

	public CustomWebAuthenticationDetails() {
		super(null);
	}
	
	public CustomWebAuthenticationDetails(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}

}
