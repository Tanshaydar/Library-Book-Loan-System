package com.bbm487.tansel.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.table.AbstractTableModel;

import com.bbm487.tansel.sql.EnumValues.USER_ROLE;

public class UserTableModel extends AbstractTableModel{

	public static final String[] columnNames = {
			"User ID",
			"User Name",
			"User Role"
	};
	
	private List<User> data;
	
	public UserTableModel() {
		super();
		data = new CopyOnWriteArrayList<User>();
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
			return Integer.class;
		case 1:
			return String.class;
		case 2:
			return USER_ROLE.class;
		default:
			return String.class;
		}
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(data.size() == 0 || rowIndex >= data.size()) {
			return null;
		}
		
		User user = data.get(rowIndex);
		Object result = null;
		
		switch (columnIndex) {
		case 0:
			result = user.getUserId();
			break;
		case 1:
			result = user.getUserName();
			break;
		case 2:
			result = user.getUserRole();
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
		if(data.size() == 0) {
			return;
		}
		data.clear();
		fireTableRowsDeleted(0, data.size());
	}
	
	public void addUser(User user){
		data.add(user);
		fireTableDataChanged();
	}
	
	public List<User> getData() {
		return data;
	}

}
