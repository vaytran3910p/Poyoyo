package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/poyoyo/users")
public class UserController {
	
	@GetMapping("/testApi")
	public String testApi() {
		return "SUCCESS";
	}
}
