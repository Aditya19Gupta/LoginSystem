package com.loginsystem.controller;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.loginsystem.entities.User;
import com.loginsystem.repository.UserRepo;

@Controller
@RequestMapping("/user")
public class LogContoller {
	@Autowired
	private UserRepo repo;
	
	@GetMapping("/dashboard")
	public String getDashboard(Model m,Principal p) {
		
		User user = this.repo.findUserByEmail(p.getName());
		
		m.addAttribute("user",user.getName());
		m.addAttribute("title","LoginSystem-Dashboard");
		
		return "dashboard";
	}

}
