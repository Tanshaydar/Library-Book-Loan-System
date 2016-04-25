package com.bbm487.tansel.model;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.table.AbstractTableModel;

import com.google.inject.Inject;

public class BookCheckoutTableModel extends AbstractTableModel{

	public static final String[] columNames = {
			"Checkout ID",
			"User ID",
			"Book Barcode",
			"Checkout Date",
			"Return Date"
	};
	
	private List<Checkout> data;
	
	@Inject
	public BookCheckoutTableModel() {
		super();
		data = new CopyOnWriteArrayList<Checkout>();
	}
	
	@Override
	public int getColumnCount() {
		return columNames.length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return Integer.class;
		case 1:
			return Integer.class;
		case 2:
			return Integer.class;
		case 3:
			return Date.class;
		case 4:
			return Date.class;
		default:
			return String.class;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(data.size() == 0 || rowIndex >= data.size()){
			return null;
		}
		Checkout checkout = data.get(rowIndex);
		Object result = null;
		switch (columnIndex) {
		case 0:
			result = checkout.getCheckout_id();
			break;
		case 1:
			result = checkout.getUser_id();
			break;
		case 2:
			result = checkout.getBook_id();
			break;
		case 3:
			result = checkout.getCheckoutDate();
			break;
		case 4:
			result = checkout.getReturnDate();
			break;
		default:
			break;
		}
		return result;
	}
	
	@Override
	public String getColumnName(int column) {
		return columNames[column];
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	public void clearTable(){
		if(data.size() == 0){
			return;
		}
		data.clear();
		fireTableRowsDeleted(0, data.size());
	}

	public void addCheckout(Checkout checkout){
		data.add(checkout);
		fireTableDataChanged();
	}

	public List<Checkout> getData() {
		return data;
	}

	public void remove(Checkout checkout) {
		data.remove(checkout);
		fireTableDataChanged();
	}
}

