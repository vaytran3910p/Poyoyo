package com.example.demo.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.util.StringUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtUtils jwtUtil; 
	
	@Autowired
	private UserDetailsServiceImpl service;
	
	/*
	 * What JwtAuthenticateFilter do
	 * intecept each http request 
	 * extract the jwt token from the Authorization header
	 * validate the token
	 * if valid sets the authentication in the spring security context
	 * */
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		
		String token = null; 
		String username =  null;
		
		// 1. extract token from Authorization header
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			token= authHeader.substring(7); // remove "Bearer "
			username =jwtUtil.extractUerName(token);
		}
		
		// 2.Authenticate if user name is valid and not already authenticated
		if (!StringUtils.isNullOrEmpty(username) && SecurityContextHolder.getContext().getAuthentication()  == null) {
			UserDetails userDetail =  service.loadUserByUsername(username);
			
			if(jwtUtil.validateJwtToken(token,userDetail)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
						userDetail,null,userDetail.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				// 3. set authentication in securityContext
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);
	}
	// read JWT from the Authorization header
	// validate the token
	// Set Authentication in the SecurityContext
}
