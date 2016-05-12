package com.bbm487.tansel.view;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.bbm487.tansel.model.BookCheckoutTableModel;
import com.bbm487.tansel.model.Checkout;
import com.google.inject.Inject;

import net.miginfocom.swing.MigLayout;

public class CheckoutListPanel extends JPanel{
	
	private BookCheckoutTableModel bookCheckoutTableModel;
	private JTable checkoutListTable;

	@Inject
	public CheckoutListPanel() {
		setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 0,grow");
		
		bookCheckoutTableModel = new BookCheckoutTableModel();
		checkoutListTable = new JTable(bookCheckoutTableModel);
		checkoutListTable.setFillsViewportHeight(true);
		scrollPane.setViewportView(checkoutListTable);
	}
	
	public void fillTable(List<Checkout> checkoutList){
		bookCheckoutTableModel.clearTable();
		for (Checkout checkout : checkoutList) {
			bookCheckoutTableModel.addCheckout(checkout);
		}
	}
	
	public void removeUserColumns() {
		if(checkoutListTable.getColumnCount() == 5) {
			checkoutListTable.removeColumn(checkoutListTable.getColumnModel().getColumn(0));
			checkoutListTable.removeColumn(checkoutListTable.getColumnModel().getColumn(0));			
		}
	}
	
	public BookCheckoutTableModel getBookCheckoutTableModel() {
		return bookCheckoutTableModel;
	}
	
	public JTable getCheckoutListTable() {
		return checkoutListTable;
	}
	
}
