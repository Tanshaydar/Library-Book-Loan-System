package com.bbm487.tansel.view;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.bbm487.tansel.model.User;
import com.bbm487.tansel.model.UserTableModel;
import com.google.inject.Inject;

import net.miginfocom.swing.MigLayout;

public class UserListWindow extends JDialog{
	
	private JPanel contentPane;
	
	private UserTableModel userTableModel;
	private JTable userTable;
	private JScrollPane scrollPaneUserList;
	private JButton buttonAddNewUser;
	private JButton buttonEditSelectedUser;
	private JButton buttonDeleteSelectedUser;
	
	@Inject
	public UserListWindow(MainWindow mainWindow) {
		super(mainWindow, "User List");
		setModal(true);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow][grow]", "[grow][30px]"));
		
		userTableModel = new UserTableModel();
		userTable = new JTable(userTableModel);
		userTable.setFillsViewportHeight(true);
		userTable.setRowHeight(30);
		
		scrollPaneUserList = new JScrollPane(userTable);
		contentPane.add(scrollPaneUserList, "cell 0 0 3 1,grow");
		
		buttonAddNewUser = new JButton("Add New User");
		contentPane.add(buttonAddNewUser, "cell 0 1,grow");
		
		buttonEditSelectedUser = new JButton("Edit Selected User");
		contentPane.add(buttonEditSelectedUser, "cell 1 1,grow");
		
		buttonDeleteSelectedUser = new JButton("Delete Selected");
		contentPane.add(buttonDeleteSelectedUser, "cell 2 1,grow");
		
		pack();
	}
	
	public void fillTable(List<User> userList){
		for (User user : userList) {
			userTableModel.addUser(user);
		}
	}

}
