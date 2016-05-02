package com.bbm487.tansel.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.bbm487.tansel.builder.BookBuilder;
import com.bbm487.tansel.builder.CheckoutBuilder;
import com.bbm487.tansel.model.Book;
import com.bbm487.tansel.model.Checkout;
import com.bbm487.tansel.model.User;
import com.bbm487.tansel.sql.EnumValues.BOOK_AVAILABILITY;
import com.bbm487.tansel.sql.EnumValues.USER_ROLE;
import com.bbm487.tansel.sql.SqlExecutions;
import com.bbm487.tansel.utility.LoggedUserInformation;
import com.bbm487.tansel.view.CheckoutListPanel;
import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;

public class CheckoutListController {

	private CheckoutListPanel checkoutListPanel;
	private SqlExecutions sqlExecutions;
	private LoggedUserInformation loggedUserInformation;
	private BookController bookController;
	private EventBus eventBus;
	
	@Inject
	public CheckoutListController(CheckoutListPanel checkoutListPanel,
			SqlExecutions sqlExecutions,
			LoggedUserInformation loggedUserInformation,
			BookController bookController,
			EventBus eventBus) {
		this.checkoutListPanel = checkoutListPanel;
		this.sqlExecutions = sqlExecutions;
		this.loggedUserInformation = loggedUserInformation;
		this.bookController = bookController;
		this.eventBus = eventBus;
		
		addMouseListenerToTable();
	}
	
	private void addMouseListenerToTable(){
		checkoutListPanel.getCheckoutListTable().addMouseListener( new MouseListener() {
			
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
					if(checkoutListPanel.getCheckoutListTable().getSelectedRow() > -1){
						int row = checkoutListPanel.getCheckoutListTable().convertRowIndexToModel(checkoutListPanel.getCheckoutListTable().getSelectedRow());
						ResultSet result = sqlExecutions.getBookByBarcode(checkoutListPanel.getBookCheckoutTableModel().getCheckout(row).getBook_id());
						
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
						if( book != null){
							JDialog parent = (JDialog) SwingUtilities.getWindowAncestor(checkoutListPanel);
							bookController.showWindow((JFrame) parent.getOwner(), book, false);
						} else {
							System.err.println("asdfasfdasdfasfdas");
						}
					}
				}
			}
		});
	}

	public void showWindow(JFrame parent, User user) {
		JDialog dialog = new JDialog(parent);
		dialog.setModal(true);
		fillCheckoutTable(user);
		dialog.setContentPane(checkoutListPanel);
		dialog.pack();
		dialog.setLocationRelativeTo(parent);
		dialog.setVisible(true);
	}


	private void fillCheckoutTable(User user){
		List<Checkout> checkoutList = new ArrayList<Checkout>();
		ResultSet resultSet = sqlExecutions.getCheckoutList(user);
		
		try {
			while(resultSet.next()) {
				checkoutList.add(
							new CheckoutBuilder()
							.setCheckoutId(resultSet.getInt("checkout_id"))
							.setUserId(user.getUserId())
							.setBookId(resultSet.getInt("book_id"))
							.setCheckoutDate(resultSet.getTimestamp("checkout_date"))
							.setReturnDate(resultSet.getTimestamp("return_date"))
							.createCheckout()
						);
			}
			checkoutListPanel.fillTable(checkoutList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(loggedUserInformation.getLoggedUser().getUserRole() != USER_ROLE.LIBRARIAN) {
			checkoutListPanel.removeUserColumns();
		} 
	}
	
}
