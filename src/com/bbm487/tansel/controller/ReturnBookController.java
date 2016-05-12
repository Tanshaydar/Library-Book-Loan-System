package com.bbm487.tansel.controller;

import javax.swing.JFrame;

import com.bbm487.tansel.sql.SqlExecutions;
import com.bbm487.tansel.utility.LoggedUserInformation;
import com.bbm487.tansel.view.ReturnBookWindow;
import com.google.inject.Inject;

public class ReturnBookController {

	private CheckoutListController checkoutListController;
	private ReturnBookWindow returnBookWindow;
	private SqlExecutions sqlExecutions;
	private LoggedUserInformation loggedUserInformation;
	
	@Inject
	public ReturnBookController(ReturnBookWindow returnBookWindow,
			CheckoutListController checkoutListController,
			SqlExecutions sqlExecutions,
			LoggedUserInformation loggedUserInformation) {
		this.returnBookWindow = returnBookWindow;
		this.checkoutListController = checkoutListController;
		this.sqlExecutions = sqlExecutions;
		this.loggedUserInformation = loggedUserInformation;
		
		returnBookWindow.add(checkoutListController.getCheckoutListPanel(), "cell 0 2 2 1,grow");
		
		addActionListenerToButtonReturnBook();
	}
	
	private void addActionListenerToButtonReturnBook(){
		returnBookWindow.addActionListenerToButtonReturnBook(ae -> {
			sqlExecutions.returnBook(loggedUserInformation.getLoggedUser(), Integer.parseInt(returnBookWindow.getTextFieldBarcode().getText()));
			
		});
	}
	
	public void showWindow(JFrame parent){
		checkoutListController.fillCheckoutTable(loggedUserInformation.getLoggedUser());
		
		returnBookWindow.pack();
		returnBookWindow.setLocationRelativeTo(parent);
		returnBookWindow.setVisible(true);
	} 
	
}
