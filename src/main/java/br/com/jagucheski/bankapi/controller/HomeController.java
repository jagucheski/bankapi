package br.com.jagucheski.bankapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

//TODO [excluir action /home] 
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
}
