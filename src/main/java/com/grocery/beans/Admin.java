package com.grocery.beans;

public class Admin {
	private int adminId;


	private String adminName;

	private String phoneNumber;

	private String password;

	private String email;
	
	public Admin(int adminId, String adminName, String phoneNumber,String email, String password) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.email = email;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setAdminname(String adminName) {
		this.adminName = adminName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", phoneNumber=" + phoneNumber + ", password="
				+ password + ", email=" + email + "]";
	}


}
