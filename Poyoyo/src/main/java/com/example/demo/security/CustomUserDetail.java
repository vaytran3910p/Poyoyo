package com.example.demo.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.Role;
import com.example.demo.model.User;

public class CustomUserDetail implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private final User user;
	
	public CustomUserDetail (User user) {
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Role> roles = user.getRoles();
		return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole().name()))
				.collect(Collectors.toSet());
	}

	@Override
	public String getPassword() {
		return user.getPassWord();
	}

	@Override
	public String getUsername() {
		return user.getUserName();
	}
	
	@Override
	public boolean isAccountNonExpired(){
		return true;
	}
	// TODO: ngừng làm chỗ này 1 chút, để qua 1 cái mục do TODO entity

}
