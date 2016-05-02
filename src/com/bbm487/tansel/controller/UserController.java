package com.bbm487.tansel.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.bbm487.tansel.builder.CheckoutBuilder;
import com.bbm487.tansel.event.UserEvent;
import com.bbm487.tansel.model.Checkout;
import com.bbm487.tansel.model.User;
import com.bbm487.tansel.sql.SqlExecutions;
import com.bbm487.tansel.view.UserWindow;
import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;

public class UserController {

	private UserWindow userWindow;
	private SqlExecutions sqlExecutions;
	private EventBus eventBus;
	
	@Inject
	public UserController(UserWindow userWindow, 
			SqlExecutions sqlExecutions,
			EventBus eventBus) {
		this.userWindow = userWindow;
		this.sqlExecutions = sqlExecutions;
		this.eventBus = eventBus;
		
		addActionListenerToSave();
		addActionListenerToDelete();
		addActionListenerToEdit();
	}

	public void showWindow(JFrame parent) {
		userWindow.clean();
		userWindow.getButtonSaveUser().setVisible(true);
		userWindow.setLocationRelativeTo(parent);
		userWindow.setEditable(true);
		userWindow.setVisible(true);
	}
	
	public void showWindow(JFrame parent, User user, boolean startEditable, boolean dontShowEditButton){
		userWindow.clean();
		userWindow.setUser(user);
		userWindow.setLocationRelativeTo(parent);
		userWindow.setEditable(startEditable);
		if(dontShowEditButton) {
			dontShowEditButton();
		}
		userWindow.setVisible(true);
	}
	
	private void addActionListenerToSave(){
		userWindow.addActionListenerToSaveButton(ae -> {
			User user = userWindow.getUser();
			if(user == null){
				return;
			}
			if(user.getUserId() < 0) {
				boolean resultSet = sqlExecutions.addUser(user);
				if(!resultSet){
					JOptionPane.showMessageDialog(userWindow, "User is successfully added!", "Success!", JOptionPane.INFORMATION_MESSAGE);
					userWindow.setVisible(false);
					eventBus.post(new UserEvent());
				} else {
					JOptionPane.showMessageDialog(userWindow, "User could not be added!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				boolean resultSet = sqlExecutions.updateUser(user);
				if(!resultSet){
					JOptionPane.showMessageDialog(userWindow, "User is successfully edited!", "Success!", JOptionPane.INFORMATION_MESSAGE);
					userWindow.setVisible(false);
					eventBus.post(new UserEvent());
				} else {
					JOptionPane.showMessageDialog(userWindow, "User could not be edited!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	private void addActionListenerToDelete(){
		userWindow.addActionListenerToDeleteButton(ae -> {
			int option = JOptionPane.showConfirmDialog(userWindow, "Are you sure you want to delete this user?", "Delete User?", JOptionPane.YES_NO_OPTION);
			if(option == JOptionPane.OK_OPTION) {
			User user = userWindow.getUser();
				boolean resultSet = sqlExecutions.deleteUser(user);
				if(!resultSet){
					JOptionPane.showMessageDialog(userWindow, "User is successfully deleted!", "Success!", JOptionPane.INFORMATION_MESSAGE);
					userWindow.setVisible(false);
					eventBus.post(new UserEvent());
				} else {
					JOptionPane.showMessageDialog(userWindow, "User could not be deleted!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	private void addActionListenerToEdit(){
		userWindow.addActionListenerToEditButton(ae -> {
			dontShowEditButton();
		});
	}
	
	private void dontShowEditButton(){
		userWindow.setEditable(true);
		userWindow.getButtonSaveUser().setVisible(true);
		userWindow.getButtonDeleteUser().setVisible(true);
		userWindow.getButtonEditUser().setVisible(false);
	}
	
	public void updateCheckOuts(User user){
		ResultSet resultSet = sqlExecutions.getCheckoutList(user);
		List<Checkout> checkouts = new ArrayList<Checkout>();
		try {
			while(resultSet.next()) {
				checkouts.add(
						new CheckoutBuilder()
						.setCheckoutId(resultSet.getInt("checkout_id"))
						.setUserId(user.getUserId())
						.setBookId(resultSet.getInt("book_id"))
						.setCheckoutDate(resultSet.getTimestamp("checkout_date"))
						.setReturnDate(resultSet.getTimestamp("return_date"))
						.createCheckout()
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		user.setCheckOuts(checkouts);
	}
	
}
