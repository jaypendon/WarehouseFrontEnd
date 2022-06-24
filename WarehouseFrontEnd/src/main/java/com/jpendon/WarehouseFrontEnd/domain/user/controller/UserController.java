package com.jpendon.WarehouseFrontEnd.domain.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jpendon.WarehouseFrontEnd.domain.user.model.User;
import com.jpendon.WarehouseFrontEnd.domain.user.service.IUserService;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	public UserController() {
		
	}

	@GetMapping("")
	public String getUsersList(Model model) {
		
		User[] userList = userService.getUsers();
		
		model.addAttribute("users", userList);
		
		return "users/listusers"; 
	}
	
	@GetMapping("/newUser")
	public String showNewUserForm(Model model) {
		User user = new User();
		
		model.addAttribute("user", user);
		
		return "users/userForm"; 
	}
	
	@PostMapping("/save")
	public String saveNewUser(@ModelAttribute("user") User user) {
		userService.createUser(user);
		
		return "redirect:/users/";
	}
	
	@GetMapping("/update")
	public String showUpdateForm(@RequestParam("userId") Long id, Model model) {
		User user = userService.getUserById(id);
		
		model.addAttribute("user", user);
		
		return "users/userForm";
	}
	
	@GetMapping("/delete")
	public String deleteUser(@RequestParam("userId") Long id) {
		userService.deleteUser(id);
		
		return "redirect:/users";
	}

}
