package com.bbm487.tansel.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.bbm487.tansel.event.UserEvent;
import com.bbm487.tansel.model.User;
import com.bbm487.tansel.model.UserTableModel;
import com.bbm487.tansel.sql.EnumValues.USER_ROLE;
import com.bbm487.tansel.sql.SqlExecutions;
import com.bbm487.tansel.view.UserListWindow;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;

public class UserListController {

	private UserListWindow userListWindow;
	private UserController userController;
	private SqlExecutions sqlExecutions;
	
	@Inject
	public UserListController(UserListWindow userListWindow,
			UserController userController,
			SqlExecutions sqlExecutions) {
		this.userListWindow = userListWindow;
		this.userController = userController;
		this.sqlExecutions = sqlExecutions;
		
		addActionListeners();
	}
	
	private void addActionListeners(){
		userListWindow.addActionListenerToButtonAddUser(ae -> {
			userController.showWindow((JFrame) userListWindow.getParent());
		});
		
		userListWindow.addActionListenerToButtonEditUser(ae -> {
			if(userListWindow.getUserTable().getSelectedRow() > -1){
				int row = userListWindow.getUserTable().convertRowIndexToModel(userListWindow.getUserTable().getSelectedRow());
				UserTableModel userTableModel = (UserTableModel) userListWindow.getUserTable().getModel();
				User user = userTableModel.getData().get(row);
				if( user != null){
					userController.showWindow((JFrame) userListWindow.getParent(), user, true, true);
				}
			}
		});
		
		userListWindow.addActionListenerToButtonDeleteUser(ae -> {
			if(userListWindow.getUserTable().getSelectedRow() > -1){
				int row = userListWindow.getUserTable().convertRowIndexToModel(userListWindow.getUserTable().getSelectedRow());
				UserTableModel userTableModel = (UserTableModel) userListWindow.getUserTable().getModel();
				User user = userTableModel.getData().get(row);
				if( user != null){
					boolean resultSet = sqlExecutions.deleteUser(user);
					if(!resultSet){
						JOptionPane.showMessageDialog(userListWindow, "User is successfully deleted!", "Success!", JOptionPane.INFORMATION_MESSAGE);
						fillUserTable();
					} else {
						JOptionPane.showMessageDialog(userListWindow, "User could not be deleted!", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
			} 
		});
		
		userListWindow.addMouseListenerToUserTable(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() >= 2){
					if(userListWindow.getUserTable().getSelectedRow() > -1){
						int row = userListWindow.getUserTable().convertRowIndexToModel(userListWindow.getUserTable().getSelectedRow());
						UserTableModel userTableModel = (UserTableModel) userListWindow.getUserTable().getModel();
						User user = userTableModel.getData().get(row);
						if( user != null){
							userController.showWindow((JFrame) userListWindow.getParent(), user, false, false);
						}
					}
				}
			}
		});
	}
	

	public void showWindow(JFrame parent) {
		fillUserTable();
		userListWindow.setLocationRelativeTo(parent);
		userListWindow.setVisible(true);
	}
	
	private void fillUserTable(){
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
	}
	
	@Subscribe
	public void handleUserEvent(UserEvent userEvent){
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				fillUserTable();
			}
		});
	}
}
