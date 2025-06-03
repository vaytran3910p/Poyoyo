package com.example.demo.model.eNum;

public enum TodoStatus {
	PENDING("PENDING"),
	IN_PROGRESS(" In progress"),
	COMPLETED ("Completed"),
	CANCELLED ("Cancelled");

	private final String displayName;
	TodoStatus(String displayName) {
		this.displayName = displayName;
	}
	public String getDisplayName() {
		return displayName;
	}
}
