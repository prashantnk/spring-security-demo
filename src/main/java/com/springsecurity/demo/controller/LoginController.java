package com.springsecurity.demo.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	@GetMapping("/showLoginPage")
	public String showLoginPage() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (!(auth instanceof AnonymousAuthenticationToken))
			return "redirect:/employees";

		return "plain-login";
//		return "bootstrap-login";
	}

	@GetMapping("/access-denied")
	public String accessDenied() {
		return "access-denied";
	}
}
