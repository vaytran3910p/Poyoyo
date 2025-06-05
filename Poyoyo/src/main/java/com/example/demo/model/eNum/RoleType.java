package com.example.demo.model.eNum;

public enum RoleType {
	ROLE_ADMIN("ADMIN"),
	ROLE_USER("USER");
	
	private final String displayName;
	RoleType(String displayName) {
		this.displayName = displayName;
	}
	public String getDisplayName() {
		return displayName;
	}
}
