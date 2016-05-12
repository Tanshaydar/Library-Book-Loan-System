package com.bbm487.tansel.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.bbm487.tansel.builder.BookBuilder;
import com.bbm487.tansel.builder.CheckoutBuilder;
import com.bbm487.tansel.event.BookAvailableEvent;
import com.bbm487.tansel.event.BookLateEvent;
import com.bbm487.tansel.event.CheckoutEvent;
import com.bbm487.tansel.event.UserEvent;
import com.bbm487.tansel.model.Book;
import com.bbm487.tansel.model.Checkout;
import com.bbm487.tansel.model.Fine;
import com.bbm487.tansel.model.FinesEvent;
import com.bbm487.tansel.model.User;
import com.bbm487.tansel.model.Waiting;
import com.bbm487.tansel.sql.EnumValues.BOOK_AVAILABILITY;
import com.bbm487.tansel.sql.SqlExecutions;
import com.bbm487.tansel.view.UserWindow;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
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
		List<Fine> fines = new ArrayList<Fine>();
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
				Checkout checkout = checkouts.get(checkouts.size() - 1); 
				if(checkout.getReturn_date() == null){
					int days = (int) getDateDiff(checkout.getCheckout_date(), Calendar.getInstance().getTime(), TimeUnit.DAYS);
					if(days >= 15) {
						ResultSet result = sqlExecutions.getBookByBarcode(checkout.getBook_id());
						Book book = null;
						try {
							result.next();
							book = new BookBuilder()
									.setBarcode(result.getInt("barcode"))
									.setName(result.getString("name"))
									.setAuthor(result.getString("author"))
									.setInformation(result.getString("information"))
									.setAvailability(BOOK_AVAILABILITY.values()[result.getInt("available")])
									.createBook();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						eventBus.post(new BookLateEvent(book));
						fines.add(new Fine(book, days - 15, (days - 15) * 100));
					}
					eventBus.post(new FinesEvent(fines));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		user.setCheckOuts(checkouts);
	}
	
	public void updateWaitingList(User user){
		ResultSet resultSet = sqlExecutions.getWaitingList(user);
		List<Waiting> waitingList = new ArrayList<Waiting>();
		try {
			while(resultSet.next()) {
				waitingList.add(new Waiting(
						resultSet.getInt("waitinglist_id"),
						resultSet.getInt("user_id"),
						resultSet.getInt("book_id")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		user.setWaitingList(waitingList);
		
		for (Waiting waiting : waitingList) {
			ResultSet result = sqlExecutions.getBookByBarcode(waiting.getBook_id());
			Book book = null;
			try {
				result.next();
				book = new BookBuilder()
						.setBarcode(result.getInt("barcode"))
						.setName(result.getString("name"))
						.setAuthor(result.getString("author"))
						.setInformation(result.getString("information"))
						.setAvailability(BOOK_AVAILABILITY.values()[result.getInt("available")])
						.createBook();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if(book.getAvailable() == BOOK_AVAILABILITY.AVAILABLE){
				eventBus.post(new BookAvailableEvent(book));
			}
		}
	}
	
	@Subscribe
	public void handleCheckoutEvent(CheckoutEvent event){
		updateCheckOuts(event.getUser());
	}
	
	private long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}
}
