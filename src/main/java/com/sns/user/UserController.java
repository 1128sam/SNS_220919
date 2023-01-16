package com.sns.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {
	// http://localhost:8080/user/sign_up_view
	@GetMapping("/sign_up_view")
	public String signUpView() {
		// model.addAttribute("viewName", "user/signUp");
		return "user/signUp";
	}

	// http://localhost:8080/user/sign_in_view
	@GetMapping("/sign_in_view")
	public String signInView() {
		// model.addAttribute("viewName", "user/signIn");
		return "user/signIn";
	}
}