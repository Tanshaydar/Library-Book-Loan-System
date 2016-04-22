package com.bbm487.tansel.utility;

import com.bbm487.tansel.model.User;
import com.bbm487.tansel.sql.EnumValues.USER_ROLE;
import com.google.inject.Inject;

public class LoggedUserInformation {

	private User loggedUser;
	
	@Inject
	public LoggedUserInformation() {
		loggedUser = null;
	}
	
	public User getLoggedUser() {
		if(loggedUser == null) {
			User user = new User();
			user.setUserRole(USER_ROLE.GUEST);
			return user;
		}
		return loggedUser;
	}
	
	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}
	
}
