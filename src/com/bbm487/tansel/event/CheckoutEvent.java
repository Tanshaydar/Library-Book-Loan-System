package com.bbm487.tansel.event;

import com.bbm487.tansel.model.User;

public class CheckoutEvent {

	private User user;
	
	public CheckoutEvent(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
}
