package com.bbm487.tansel.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.table.AbstractTableModel;

import com.bbm487.tansel.sql.EnumValues.BOOK_AVAILABILITY;

public class BookSearchTableModel extends AbstractTableModel{

	public static final String[] columNames = {
			"Book Name",
			"Author",
			"Information",
			"Available"
	};
	
	private List<Book> data;
	
	public BookSearchTableModel() {
		super();
		data = new CopyOnWriteArrayList<Book>();
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
			return String.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		case 3:
			return BOOK_AVAILABILITY.class;
		default:
			return String.class;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(data.size() == 0 || rowIndex >= data.size()){
			return null;
		}
		Book book = data.get(rowIndex);
		Object result = null;
		switch (columnIndex) {
		case 0:
			result = book.getName();
			break;
		case 1:
			result = book.getAuthor();
			break;
		case 2:
			result = book.getInformation();
			break;
		case 3:
			result = BOOK_AVAILABILITY.values()[book.getAvailable()].name();
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

	public void addBook(Book book){
		data.add(book);
		fireTableDataChanged();
	}
}
