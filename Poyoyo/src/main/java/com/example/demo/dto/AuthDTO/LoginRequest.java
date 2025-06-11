package com.example.demo.dto.AuthDTO;

import lombok.Data;

@Data
public class LoginRequest {
	private String username;
	private String password;
}
