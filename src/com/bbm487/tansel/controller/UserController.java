package com.bbm487.tansel.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
				if(user == null){
					return;
				}
				if(user.getUserId() < 0) {
					boolean resultSet = sqlExecutions.addUser(user);
					if(!resultSet){
						JOptionPane.showMessageDialog(userWindow, "User is successfully added!", "Success!", JOptionPane.INFORMATION_MESSAGE);
						userWindow.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(userWindow, "User could not be added!", "Error!", JOptionPane.ERROR_MESSAGE);
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
