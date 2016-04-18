package com.bbm487.tansel.event;

import com.bbm487.tansel.model.User;

public class LoginEvent {

	private User user;
	
	public LoginEvent(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
	
}
