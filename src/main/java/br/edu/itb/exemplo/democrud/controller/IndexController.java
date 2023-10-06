package br.edu.itb.exemplo.democrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1")
public class IndexController {

	@GetMapping("/index")
	public String index() {
		return "index";
	}

	@GetMapping("/home")
	public String homePage() {
		return "home";
	}
	
	@GetMapping("/contact")
	public String contactPage() {
		return "contact";
	}

	@GetMapping("/about")
	public String aboutPage() {
		return "about";
	}

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

}
