package com.notes.model;

public class UserId {
	private String id;

	// Default constructor
	public UserId() {
	}

	// String-argument constructor
	public UserId(String id) {
		this.id = id;
	}

	// Getter
	public String getId() {
		return id;
	}

	// Setter
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return id;
	}
}
