package com.jpendon.WarehouseFrontEnd.infrastructure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	
	@GetMapping("/")
	public String getHome(Model model) {
		model.addAttribute("date", new java.util.Date());
		
		return "helloworld";

	}
}
