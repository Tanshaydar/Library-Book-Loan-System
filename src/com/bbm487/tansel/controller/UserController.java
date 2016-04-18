package com.bbm487.tansel.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;

import com.bbm487.tansel.model.User;
import com.bbm487.tansel.sql.SqlExecutions;
import com.bbm487.tansel.view.UserWindow;
import com.google.inject.Inject;

public class UserController {

	private UserWindow userWindow;
	private SqlExecutions sqlExecutions;
	
	@Inject
	public UserController(UserWindow userWindow, SqlExecutions sqlExecutions) {
		this.userWindow = userWindow;
		this.sqlExecutions = sqlExecutions;
		
		addActionListenerToSave();
		addActionListenerToDelete();
		addActionListenerToEdit();
	}

	public void showWindow(JFrame parent) {
		userWindow.clean();
		userWindow.getButtonSaveUser().setVisible(true);
		userWindow.setLocationRelativeTo(parent);
		userWindow.setVisible(true);
	}
	
	public void showWindow(JFrame parent, User user){
		userWindow.clean();
		userWindow.setUser(user);
		userWindow.setLocationRelativeTo(parent);
		userWindow.setVisible(true);
	}
	
	private void addActionListenerToSave(){
		userWindow.getButtonSaveUser().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				User user = userWindow.getUser();
				if(user.getUserId() < 0) {
					ResultSet resultSet = sqlExecutions.addUser(user);
					try {
						if(resultSet.next()) {
							System.err.println(resultSet.getString("username"));
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					System.err.println(user.getUserId());
					System.err.println(user.getUserName());
					System.err.println(user.getPassword());
					System.err.println(user.getUserRole());
				}
				
			}
		});
	}
	private void addActionListenerToDelete(){
		userWindow.getButtonDeleteUser().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
	private void addActionListenerToEdit(){
		userWindow.getButtonEditUser().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userWindow.getButtonSaveUser().setVisible(true);
				userWindow.getButtonDeleteUser().setVisible(true);
				userWindow.getButtonEditUser().setVisible(false);
			}
		});
	}
	
}
