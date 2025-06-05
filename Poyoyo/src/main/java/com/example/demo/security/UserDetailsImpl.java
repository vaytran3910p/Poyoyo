package com.example.demo.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.User;

public class UserDetailsImpl implements UserDetails {
	
	// only need what use to authenticated and authorize a user
	private final String userName;
	private final String passWord;
	private Collection<? extends GrantedAuthority> authorities;
	
	
	public UserDetailsImpl (String userName, String passWord, Collection<? extends GrantedAuthority> authorities) {
		this.userName = userName;
		this.passWord = passWord;
		this.authorities = authorities;
	}
	
	public static UserDetailsImpl build (User user) {
		List<? extends GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRole().getDisplayName()))
				.toList();
		return new UserDetailsImpl(user.getUserName(), user.getPassWord(), authorities);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return passWord;
	}

	@Override
	public String getUsername() {
		return userName;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}

}
