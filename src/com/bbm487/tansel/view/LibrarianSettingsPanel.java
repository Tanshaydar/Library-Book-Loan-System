package com.bbm487.tansel.view;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.google.inject.Inject;

import net.miginfocom.swing.MigLayout;

public class LibrarianSettingsPanel extends JPanel {

	private JButton buttonAddUser;
	private JButton buttonAddBook;
	private JButton buttonSeeUserList;
	
	@Inject
	public LibrarianSettingsPanel() {
		setLayout(new MigLayout("", "[][][]", "[]"));
		
		buttonAddUser = new JButton("Add User");
		add(buttonAddUser, "cell 0 0,growy");
		
		buttonAddBook = new JButton("Add Book");
		add(buttonAddBook, "cell 1 0,growy");
		
		buttonSeeUserList = new JButton("See User List");
		add(buttonSeeUserList, "cell 2 0,growy");
	}
	
	public JButton getButtonAddUser() {
		return buttonAddUser;
	}
	
	public JButton getButtonAddBook() {
		return buttonAddBook;
	}
	
	public JButton getButtonSeeUserList() {
		return buttonSeeUserList;
	}
}
