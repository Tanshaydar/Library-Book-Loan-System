package com.bbm487.tansel.model;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.table.AbstractTableModel;

import com.google.inject.Inject;

public class FineTableModel extends AbstractTableModel{

	public static final String[] columnNames = {
			"Book Name",
			"Days Late",
			"Cost"
	};
	
	private List<Fine> data;
	
	@Inject
	public FineTableModel() {
		super();
		data = new CopyOnWriteArrayList<Fine>();
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return String.class;
		case 1:
			return Integer.class;
		case 2:
			return Integer.class;
		default:
			return String.class;
		}
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(data.size() == 0 || rowIndex >= data.size()){
			return null;
		}
		Fine fine = data.get(rowIndex);
		Object result = null;
		switch (columnIndex) {
		case 0:
			result = fine.getBook().getName();
			break;
		case 1:
			result = fine.getDaysLate();
			break;
		case 2:
			result = fine.getCost();
			break;
		default:
			break;
		}
		return result;
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
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

	public void addFine(Fine fine){
		data.add(fine);
		fireTableDataChanged();
	}

	public List<Fine> getData() {
		return data;
	}
	
	public Fine getCheckout(int row){
		return data.get(row);
	}

	public void remove(Checkout checkout) {
		data.remove(checkout);
		fireTableDataChanged();
	}
	
}
