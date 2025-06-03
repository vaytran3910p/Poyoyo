package com.example.demo.model.eNum;

public enum Priority {
	LOW("LOW"),
	MEDIUM("MEDIUM"),
	HIGH("HIGH"),
	URGENT("URGENT");
	private final String displayName;
	
	Priority(String displayName) {
		this.displayName = displayName;
	}
	public String getDisplayName() {
		return displayName;
	}
}
