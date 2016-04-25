package com.bbm487.tansel.controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.bbm487.tansel.event.BookEvent;
import com.bbm487.tansel.event.UserEvent;
import com.bbm487.tansel.model.Book;
import com.bbm487.tansel.sql.SqlExecutions;
import com.bbm487.tansel.utility.LoggedUserInformation;
import com.bbm487.tansel.view.BookWindow;
import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;

public class BookController {

	private BookWindow bookWindow;
	private SqlExecutions sqlExecutions;
	private LoggedUserInformation loggedUserInformation;
	private EventBus eventBus;

	@Inject
	public BookController(BookWindow bookWindow,
			SqlExecutions sqlExecutions,
			LoggedUserInformation loggedUserInformation,
			EventBus eventBus) {
		this.bookWindow = bookWindow;
		this.sqlExecutions = sqlExecutions;
		this.loggedUserInformation = loggedUserInformation;
		this.eventBus = eventBus;
		
		addActionListenerToSave();
		addActionListenerToEdit();
		addActionListenerToDelete();
		addActionListenerToCheckout();	
		addActionlistenerToWaitingList();
	}
	
	public void showWindow(JFrame parent, boolean editable) {
		bookWindow.clean();
		bookWindow.setLocationRelativeTo(parent);
		bookWindow.setEditable(editable, loggedUserInformation.getLoggedUser());
		bookWindow.setVisible(true);
	}
	
	public void showWindow(JFrame parent, Book book, boolean editable) {
		bookWindow.clean();
		bookWindow.setBook(book);
		bookWindow.setLocationRelativeTo(parent);
		bookWindow.setEditable(editable, loggedUserInformation.getLoggedUser());
		bookWindow.setVisible(true);
	}
	
	public void addActionListenerToSave(){
		bookWindow.addActionListenerToSaveButton(ae -> {
			Book book = bookWindow.getBook();
			if(book == null){
				return;
			}
			if(book.getBarcode() < 0) {
				boolean result = sqlExecutions.addBook(book);
				if(!result){
					JOptionPane.showMessageDialog(bookWindow, "Book is successfully added!", "Success!", JOptionPane.INFORMATION_MESSAGE);
					bookWindow.setVisible(false);
					eventBus.post(new UserEvent());
				} else {
					JOptionPane.showMessageDialog(bookWindow, "Book could not be added!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				boolean result = sqlExecutions.updateBook(book);
				if(!result){
					JOptionPane.showMessageDialog(bookWindow, "Book is successfully edited!", "Success!", JOptionPane.INFORMATION_MESSAGE);
					bookWindow.setVisible(false);
					eventBus.post(new UserEvent());
				} else {
					JOptionPane.showMessageDialog(bookWindow, "Book could not be edited!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	public void addActionListenerToEdit(){
		bookWindow.addActionListenerToEditButton(ae -> {
			dontShowEditButton();
		});
	}
	public void addActionListenerToDelete(){
		bookWindow.addActionListenerToDeleteButton(ae -> {
			int option = JOptionPane.showConfirmDialog(bookWindow, "Are you sure you want to delete this book?", "Delete Book?", JOptionPane.YES_NO_OPTION);
			if(option == JOptionPane.OK_OPTION) {
				Book book = bookWindow.getBook();
				boolean result = sqlExecutions.deleteBook(book);
				if(!result){
					JOptionPane.showMessageDialog(bookWindow, "Book is successfully deleted!", "Success!", JOptionPane.INFORMATION_MESSAGE);
					eventBus.post(new BookEvent(book));
					bookWindow.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(bookWindow, "Book could not be deleted!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	public void addActionListenerToCheckout(){
		bookWindow.addActionListenerToCheckoutButton(ae -> {
			Book book = bookWindow.getBook();
			sqlExecutions.checkoutBook(book, loggedUserInformation.getLoggedUser());
			JOptionPane.showMessageDialog(bookWindow, "Book is successfully checked out! \n Happy reading!", "Success!", JOptionPane.INFORMATION_MESSAGE);
			bookWindow.setVisible(false);
		});
	}
	
	public void addActionlistenerToWaitingList(){
		bookWindow.addActionListenerToWaitingListButton(ae -> {
			Book book = bookWindow.getBook();
			sqlExecutions.addBookToWaitingList(book, loggedUserInformation.getLoggedUser());
			JOptionPane.showMessageDialog(bookWindow, "You are successfully added to book waiting list! \n "
					+ "You will be notified when this book is available!", "Success!", JOptionPane.INFORMATION_MESSAGE);
			bookWindow.setVisible(false);
		});
	}
	
	private void dontShowEditButton(){
		bookWindow.setEditable(true, loggedUserInformation.getLoggedUser());
	}
}
