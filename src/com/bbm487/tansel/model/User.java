package com.bbm487.tansel.model;

import com.bbm487.tansel.sql.EnumValues.USER_ROLE;

public class User {

	private int userId;
	private String userName;
	private String password;
	private USER_ROLE userRole;
	
	public User(int userId, String userName, USER_ROLE userRole) {
		this.userId = userId;
		this.userName = userName;
		this.userRole = userRole;
	}

	public User() {
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public USER_ROLE getUserRole() {
		return userRole;
	}
	
	public void setUserRole(USER_ROLE userRole) {
		this.userRole = userRole;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}	
}
