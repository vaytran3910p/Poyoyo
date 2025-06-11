package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AuthDTO.JwtResponse;
import com.example.demo.dto.AuthDTO.LoginRequest;
import com.example.demo.dto.AuthDTO.RegisterRequest;
import com.example.demo.security.JwtUtils;
import com.example.demo.security.UserDetailsImpl;
import com.example.demo.service.UserService;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	// /api/auth/login	  --> return JWT
	// /api/auth/register --> create user and assign roles
	
	private final AuthenticationManager authManager;
	private final UserService userService;
	private final JwtUtils jwtUtils;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
		try {
			userService.registerUser(request);
			return ResponseEntity.ok("User register successfully!");
		} catch(RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
		Authentication auth = authManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()
		));
		SecurityContextHolder.getContext().setAuthentication(auth);
		String jwt = jwtUtils.generateJwtToken(auth);
		UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
		return ResponseEntity.ok(new JwtResponse(jwt,userDetails.getUsername(),userDetails.getAuthorities())) ;
	}
}
