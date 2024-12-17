package com.notes.model;

public class UserProfileDetails {
	private UserId id;
	private String name;
	private String phone;
	private String email;
	private String company;

	public UserId getId() {
		return id;
	}

	public void setId(UserId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "User Profile Information:{ " + id + ", " + name + ", " + phone + ", " + email + ", " + ", " + company
				+ " }";
	}
}
