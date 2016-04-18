package com.bbm487.tansel.controller;

import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.bbm487.tansel.event.LoginEvent;
import com.bbm487.tansel.model.User;
import com.bbm487.tansel.sql.SqlExecutions;
import com.bbm487.tansel.sql.EnumValues.USER_ROLE;
import com.bbm487.tansel.view.LoginWindow;
import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;

public class LoginController {

	private SqlExecutions sqlExecutions;
	private LoginWindow loginWindow;
	private EventBus eventBus;
	
	@Inject
	public LoginController(SqlExecutions sqlExecutions,
			EventBus eventBus) {
		this.sqlExecutions = sqlExecutions;
		this.eventBus = eventBus;
	}
	
	public void OnLoginButtonClicked(String userName, String password) throws HeadlessException, SQLException{
		ResultSet resultSet = sqlExecutions.checkUser(userName, password);
		
		if(resultSet.next()) {
			eventBus.post(new LoginEvent(
					new User(
							resultSet.getInt("user_id"),
							resultSet.getString("username"),
							USER_ROLE.values()[resultSet.getInt("role")])));
			loginWindow.dispose();
		} else {
			JOptionPane.showMessageDialog(loginWindow, "Wrong username or password!", "Login Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void OnCancelButtonClicked(){
		loginWindow.dispose();
	}
	
	public void registerLoginWindow(LoginWindow loginWindow){
		this.loginWindow = loginWindow;
	}
}
