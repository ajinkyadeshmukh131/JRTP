package com.ad.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuthOperationsController {

	@GetMapping("/home")
	public String showHome() {
		return "Hello, Welcome to Ajinkya Deshmukh Infra World App";
	}
	
	@GetMapping("/after")
	public String afterLoginPage() {
		return "You are successfully logged in to this app. This is the second page of this app";
	}
	
	@GetMapping("/user")
	public Authentication showUserDetails(Principal principal) {
		System.out.println("logged in details::"+principal.getName());
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		return auth;
	}
	
	
}
