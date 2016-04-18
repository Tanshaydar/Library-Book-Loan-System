package com.bbm487.tansel.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.bbm487.tansel.model.User;
import com.bbm487.tansel.sql.SqlExecutions;
import com.bbm487.tansel.sql.EnumValues.USER_ROLE;
import com.bbm487.tansel.view.UserListWindow;
import com.google.inject.Inject;

public class UserListController {

	private UserListWindow userListWindow;
	private SqlExecutions sqlExecutions;
	
	@Inject
	public UserListController(UserListWindow userListWindow, SqlExecutions sqlExecutions) {
		this.userListWindow = userListWindow;
		this.sqlExecutions = sqlExecutions;
	}

	public void showWindow(JFrame parent) {
		List<User> userList = new ArrayList<User>();
		ResultSet resultSet = sqlExecutions.getUserList();
		
		try {
			while(resultSet.next()) {
				userList.add(
						new User(resultSet.getInt("user_id"), resultSet.getString("username"), USER_ROLE.values()[resultSet.getInt("role")])
						);
			}
			userListWindow.fillTable(userList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		userListWindow.setLocationRelativeTo(parent);
		userListWindow.setVisible(true);
	}
	
}
