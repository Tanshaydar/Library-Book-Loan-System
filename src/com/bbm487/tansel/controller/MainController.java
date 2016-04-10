package com.bbm487.tansel.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.bbm487.tansel.LibraryBookLoanSystemModule.LoginWindowProvider;
import com.bbm487.tansel.model.Book;
import com.bbm487.tansel.sql.SqlExecutions;
import com.bbm487.tansel.sql.SqlQueries;
import com.bbm487.tansel.sql.EnumValues.BOOK_AVAILABILITY;
import com.bbm487.tansel.view.LoginWindow;
import com.bbm487.tansel.view.MainWindow;
import com.google.inject.Inject;

public class MainController {

	private SqlExecutions sqlExecutions;
	
	private LoginWindowProvider loginWindowProvider;
	
	private MainWindow mainWindow;
	
	@Inject
	public MainController(SqlExecutions sqlExecutions,
			MainWindow mainWindow, 
			LoginWindowProvider loginWindowProvider) {
		this.sqlExecutions = sqlExecutions;
		this.mainWindow = mainWindow;
		this.loginWindowProvider = loginWindowProvider;
		
		addActionListenerToExitButton();
		addActionListenerToLoginButton();
		addActionListenerToSearchButton();
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
									resultSet.getInt("available"));
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
	
	private void addActionListenerToExitButton(){
		mainWindow.addActionListenerExitButton(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(mainWindow, "Application will close.\n Are you sure you want to exit?",
						"Are you sure?", JOptionPane.INFORMATION_MESSAGE);
				if(result == JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}
		});
	}
}
