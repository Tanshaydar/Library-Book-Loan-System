package com.bbm487.tansel.model;

import java.util.ArrayList;
import java.util.List;

import com.bbm487.tansel.sql.EnumValues.USER_ROLE;

public class User {

	private int userId;
	private String userName;
	private String password;
	private USER_ROLE userRole;
	private List<Checkout> checkOuts;
	
	public User(int userId, String userName, USER_ROLE userRole) {
		this.userId = userId;
		this.userName = userName;
		this.userRole = userRole;
		checkOuts = new ArrayList<Checkout>();
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
	
	public List<Checkout> getCheckOuts() {
		return checkOuts;
	}
	
	public void setCheckOuts(List<Checkout> checkOuts) {
		this.checkOuts = checkOuts;
	}
	
	public Checkout hasBook(Book book){
		for (Checkout checkout : checkOuts) {
			if(checkout.getBook_id() == book.getBarcode()) {
				return checkout;
			}
		}
		return null;
	}
}
