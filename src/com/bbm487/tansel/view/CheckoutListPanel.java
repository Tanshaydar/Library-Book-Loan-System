package com.bbm487.tansel.view;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.bbm487.tansel.model.BookCheckoutTableModel;
import com.google.inject.Inject;

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
		scrollPane.setViewportView(checkoutListTable);
	}
	
}
