package com.springsecurity.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springsecurity.demo.entity.User;
import com.springsecurity.demo.model.MUser;
import com.springsecurity.demo.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private UserService userService;

	@GetMapping("/showRegister")
	public String showRegisterPage(Model model) {
		model.addAttribute("MUser", new MUser());
		System.out.println("Called ShowRegister!");
		return "registration-form";
	}

	@PostMapping("/processRegistrationForm")
	public String processForm(@Valid @ModelAttribute("MUser") MUser mu, BindingResult bs, Model model) {

		if (bs.hasErrors())
			return "registration-form";

		User u = userService.findUserbyName(mu.getUsername());

		if (u != null) {
			model.addAttribute("registrationError", "User already exists !!");
			return "redirect:showRegister";
		}

		userService.save(mu);

		return "registration-confirmation";
	}

}
