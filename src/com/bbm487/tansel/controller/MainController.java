package com.bbm487.tansel.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.bbm487.tansel.LibraryBookLoanSystemModule.LoginWindowProvider;
import com.bbm487.tansel.event.LoginEvent;
import com.bbm487.tansel.model.Book;
import com.bbm487.tansel.sql.EnumValues.BOOK_AVAILABILITY;
import com.bbm487.tansel.sql.SqlExecutions;
import com.bbm487.tansel.view.BookWindow;
import com.bbm487.tansel.view.LoginWindow;
import com.bbm487.tansel.view.MainWindow;
import com.bbm487.tansel.view.UserListWindow;
import com.bbm487.tansel.view.UserWindow;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;

public class MainController {

	private SqlExecutions sqlExecutions;
	
	private LoginWindowProvider loginWindowProvider;
	
	private MainWindow mainWindow;
	
	private UserListController userListController;
	private BookController bookController;
	private UserController userController;
	
	@Inject
	public MainController(SqlExecutions sqlExecutions,
			MainWindow mainWindow, 
			LoginWindowProvider loginWindowProvider,
			UserListController userListController,
			UserController userController,
			BookController bookController) {
		this.sqlExecutions = sqlExecutions;
		this.mainWindow = mainWindow;
		this.loginWindowProvider = loginWindowProvider;
		
		this.userListController = userListController;
		this.userController = userController;
		this.bookController = bookController;
		
		addActionListenerToLoginButton();
		addActionListenerToLogoutButton();
		addActionListenerToSearchButton();
		addCustomerActionListeners();
		addLibrarianActionListeners();
	}

	public void showMainWindow(){
		mainWindow.setVisible(true);
	}
	
	private void addActionListenerToSearchButton(){
		mainWindow.addActionlistenerToSearchButton(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String bookName = mainWindow.getTextFieldBookName().getText();
				String bookAuthor = mainWindow.getTextFieldBookAuthor().getText();
				String bookInformation = mainWindow.getTextFieldInformation().getText();
				
				ResultSet resultSet = sqlExecutions.searchBooks(bookName, bookAuthor, bookInformation);

				if(resultSet == null){
					JOptionPane.showMessageDialog(mainWindow, "No book has been found!", "Search Result is Empty!", JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						while(resultSet.next()){
							Book book = new Book(
									resultSet.getInt("barcode"),
									resultSet.getString("name"),
									resultSet.getString("author"),
									resultSet.getString("information"),
									BOOK_AVAILABILITY.values()[resultSet.getInt("available")]);
							mainWindow.getBookSearchTableModel().addBook(book);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
			
		});
	}
	
	private void addActionListenerToLoginButton(){
		mainWindow.addActionListenerLoginButton(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginWindow loginWindow = loginWindowProvider.get();
				loginWindow.setLocationRelativeTo(mainWindow);
				loginWindow.setVisible(true);
			}
		});
	}
	
	private void addActionListenerToLogoutButton(){
		mainWindow.addActionlistenerLogoutButton(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainWindow.enableLogoutUiChanges();
			}
		});
	}
	
	private void addLibrarianActionListeners() {
		mainWindow.getLibrarianSettingsPanel().getButtonAddBook().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bookController.showWindow(mainWindow);
			}
		});
		
		mainWindow.getLibrarianSettingsPanel().getButtonAddUser().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				userController.showWindow(mainWindow);
			}
		});
		
		mainWindow.getLibrarianSettingsPanel().getButtonSeeUserList().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userListController.showWindow(mainWindow);
			}
		});
	}

	private void addCustomerActionListeners() {
		// TODO Auto-generated method stub
		
	}
	
	@Subscribe
	public void handleLoginEvent(LoginEvent loginEvent){
		mainWindow.enableLoginUiChanges(loginEvent.getUser());
	}
}
