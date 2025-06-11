package com.example.demo.dto.AuthDTO;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
	private String token;
	private String userName;
	private Collection<? extends GrantedAuthority> roles;
}
