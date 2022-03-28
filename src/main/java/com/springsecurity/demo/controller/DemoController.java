package com.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

	@GetMapping("/employees")
	public String homePage() {
		return "home";
	}

	@GetMapping("/")
	public String landingPage() {
		return "landing-page";
	}

	@GetMapping("/leaders")
	public String leaderPage() {
		return "leaders";
	}

	@GetMapping("/systems")
	public String systemPage() {
		return "systems";
	}

}
