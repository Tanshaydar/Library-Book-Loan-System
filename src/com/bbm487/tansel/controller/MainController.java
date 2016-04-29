package com.bbm487.tansel.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.bbm487.tansel.LibraryBookLoanSystemModule.LoginWindowProvider;
import com.bbm487.tansel.event.BookEvent;
import com.bbm487.tansel.event.LoginEvent;
import com.bbm487.tansel.model.Book;
import com.bbm487.tansel.model.BookSearchTableModel;
import com.bbm487.tansel.sql.EnumValues.BOOK_AVAILABILITY;
import com.bbm487.tansel.sql.SqlExecutions;
import com.bbm487.tansel.utility.LoggedUserInformation;
import com.bbm487.tansel.view.LoginWindow;
import com.bbm487.tansel.view.MainWindow;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;

public class MainController {

	private SqlExecutions sqlExecutions;
	
	private LoginWindowProvider loginWindowProvider;
	
	private MainWindow mainWindow;
	
	private UserListController userListController;
	private BookController bookController;
	private UserController userController;
	private CheckoutListController checkoutListController;
	
	private LoggedUserInformation loggedUserInformation;
	
	@Inject
	public MainController(SqlExecutions sqlExecutions,
			MainWindow mainWindow, 
			LoginWindowProvider loginWindowProvider,
			UserListController userListController,
			UserController userController,
			CheckoutListController checkoutListController,
			BookController bookController,
			LoggedUserInformation loggedUserInformation) {
		this.sqlExecutions = sqlExecutions;
		this.mainWindow = mainWindow;
		this.loginWindowProvider = loginWindowProvider;
		
		this.userListController = userListController;
		this.userController = userController;
		this.checkoutListController = checkoutListController;
		this.bookController = bookController;
		
		this.loggedUserInformation = loggedUserInformation;
		
		addMouseListenerToSearchTable();
		addActionListenerToLoginButton();
		addActionListenerToLogoutButton();
		addActionListenerToSearchButton();
		addCustomerActionListeners();
		addLibrarianActionListeners();
	}
	
	@Subscribe
	public void handleBookEvent(BookEvent bookEvent){
		mainWindow.getBookSearchTableModel().remove(bookEvent.getBook());
	}

	public void showMainWindow(){
		mainWindow.setVisible(true);
	}
	
	private void addMouseListenerToSearchTable(){
		mainWindow.getSearchResultTable().addMouseListener(new MouseListener() {
			
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
					if(mainWindow.getSearchResultTable().getSelectedRow() > -1){
						int row = mainWindow.getSearchResultTable().convertRowIndexToModel(mainWindow.getSearchResultTable().getSelectedRow());
						BookSearchTableModel bookSearchTableModel = (BookSearchTableModel) mainWindow.getSearchResultTable().getModel();
						Book book = bookSearchTableModel.getData().get(row);
						if( book != null){
							bookController.showWindow(mainWindow, book, false);
						}
					}
				}
			}
		});
	}
	
	private void addActionListenerToSearchButton(){
		mainWindow.addActionlistenerToSearchButton(ae -> {
			String bookName = mainWindow.getTextFieldBookName().getText();
			String bookAuthor = mainWindow.getTextFieldBookAuthor().getText();
			String bookInformation = mainWindow.getTextFieldInformation().getText();
			
			ResultSet resultSet = sqlExecutions.searchBooks(bookName, bookAuthor, bookInformation);

			if(resultSet == null){
				JOptionPane.showMessageDialog(mainWindow, "No book has been found!", "Search Result is Empty!", JOptionPane.WARNING_MESSAGE);
			} else {
				mainWindow.getBookSearchTableModel().clearTable();
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
		});
	}
	
	private void addActionListenerToLoginButton(){
		mainWindow.addActionListenerLoginButton(ae -> {
			LoginWindow loginWindow = loginWindowProvider.get();
			loginWindow.setLocationRelativeTo(mainWindow);
			loginWindow.setVisible(true);			
		});
	}
	
	private void addActionListenerToLogoutButton(){
		mainWindow.addActionlistenerLogoutButton(ae -> {
			mainWindow.enableLogoutUiChanges();
			loggedUserInformation.setLoggedUser(null);
		});
	}
	
	private void addLibrarianActionListeners() {
		mainWindow.getLibrarianSettingsPanel().getButtonAddBook().addActionListener(ae -> {
			bookController.showWindow(mainWindow, true);
		});
		
		mainWindow.getLibrarianSettingsPanel().getButtonAddUser().addActionListener(ae -> {
			userController.showWindow(mainWindow);
		});
		
		mainWindow.getLibrarianSettingsPanel().getButtonSeeUserList().addActionListener(ae -> {
			userListController.showWindow(mainWindow);
		});
	}

	private void addCustomerActionListeners() {
		mainWindow.getCustomerSettingsPanel().getButtonReturnBook().addActionListener(ae -> {
			
		});
		mainWindow.getCustomerSettingsPanel().getButtonSeeMyBookList().addActionListener(ae -> {
			checkoutListController.showWindow(mainWindow);			
		});
		mainWindow.getCustomerSettingsPanel().getButtonSeeMyFines().addActionListener(ae -> {
			
		});
	}
	
	@Subscribe
	public void handleLoginEvent(LoginEvent loginEvent){
		mainWindow.enableLoginUiChanges(loginEvent.getUser());
	}
}
