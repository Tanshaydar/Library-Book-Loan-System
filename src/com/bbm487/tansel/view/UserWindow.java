package com.bbm487.tansel.view;

import javax.swing.JDialog;

import com.bbm487.tansel.model.User;
import com.bbm487.tansel.sql.EnumValues.USER_ROLE;
import com.google.inject.Inject;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JButton;

public class UserWindow extends JDialog{

	private JLabel labelUserName;
	private JLabel labelUserPassword;
	private JLabel labelUserRole;
	private JTextField textFieldUserName;
	private JPasswordField passwordField;
	private JComboBox<USER_ROLE> comboBoxUserRole;
	private JPanel panel;
	private JButton buttonSaveUser;
	private JButton buttonEditUser;
	private JButton buttonDeleteUser;
	private JLabel labelUserId;
	private JTextField textFieldUserId;
	
	private User user;
	
	@Inject
	public UserWindow() {
		setTitle("User Management");
		setModal(true);
		getContentPane().setLayout(new MigLayout("", "[][grow]", "[][30][30][30][]"));
		
		labelUserId = new JLabel("User ID:");
		getContentPane().add(labelUserId, "cell 0 0,alignx right,aligny center,hidemode 3");
		
		textFieldUserId = new JTextField();
		textFieldUserId.setEditable(false);
		getContentPane().add(textFieldUserId, "cell 1 0,grow,hidemode 3");
		textFieldUserId.setColumns(10);
		
		labelUserName = new JLabel("User Name:");
		getContentPane().add(labelUserName, "cell 0 1,alignx right,aligny center");
		
		textFieldUserName = new JTextField();
		getContentPane().add(textFieldUserName, "cell 1 1,grow");
		textFieldUserName.setColumns(10);
		
		labelUserPassword = new JLabel("User Password:");
		getContentPane().add(labelUserPassword, "cell 0 2,alignx right,aligny center");
		
		passwordField = new JPasswordField();
		getContentPane().add(passwordField, "cell 1 2,grow");
		
		labelUserRole = new JLabel("User Role:");
		getContentPane().add(labelUserRole, "cell 0 3,alignx right,aligny center");
		
		comboBoxUserRole = new JComboBox<USER_ROLE>();
		comboBoxUserRole.addItem(USER_ROLE.CUSTOMER);
		comboBoxUserRole.addItem(USER_ROLE.LIBRARIAN);
		getContentPane().add(comboBoxUserRole, "cell 1 3,grow");
		
		panel = new JPanel();
		getContentPane().add(panel, "cell 0 4 2 1,alignx center,growy");
		panel.setLayout(new MigLayout("", "[][][]", "[]"));
		
		buttonSaveUser = new JButton("Save User");
		panel.add(buttonSaveUser, "cell 0 0,alignx center,growy,hidemode 3");
		
		buttonEditUser = new JButton("Edit User");
		panel.add(buttonEditUser, "cell 1 0,alignx center,growy,hidemode 3");
		
		buttonDeleteUser = new JButton("Delete User");
		panel.add(buttonDeleteUser, "cell 2 0,alignx center,growy,hidemode 3");
		
		pack();
	}
	
	public void setUser(User user){
		textFieldUserId.setText(Integer.toString(user.getUserId()));
		textFieldUserId.setVisible(true);
		labelUserId.setVisible(true);
		textFieldUserName.setText(user.getUserName());
		passwordField.setText(null);
		user.setUserRole(user.getUserRole());
		buttonEditUser.setVisible(true);
	}
	
	public User getUser(){
		if(user == null) {
			User user = new User();
			user.setUserId(-1);
			user.setUserName(textFieldUserName.getText());
			user.setPassword(new String(passwordField.getPassword()));
			user.setUserRole((USER_ROLE) comboBoxUserRole.getSelectedItem());
			if(textFieldUserName.getText() == null || textFieldUserName.getText().isEmpty()
					|| passwordField.getPassword().length == 0){
				JOptionPane.showMessageDialog(this, "You need to fill the fields!", "Error!", JOptionPane.ERROR_MESSAGE);
				return null;
			}
			return user;
		} else {
			user.setUserName(textFieldUserName.getText());
			user.setPassword(new String(passwordField.getPassword()));
			user.setUserRole((USER_ROLE) comboBoxUserRole.getSelectedItem());
			return user;
		}
	}

	public void clean() {
		user = null;
		textFieldUserId.setText(null);
		textFieldUserId.setVisible(false);
		labelUserId.setVisible(false);
		textFieldUserName.setText(null);
		passwordField.setText(null);
		comboBoxUserRole.setSelectedItem(USER_ROLE.CUSTOMER);
		buttonEditUser.setVisible(false);
		buttonDeleteUser.setVisible(false);
		buttonSaveUser.setVisible(false);
	}
	
	public JButton getButtonDeleteUser() {
		return buttonDeleteUser;
	}
	
	public JButton getButtonEditUser() {
		return buttonEditUser;
	}
	
	public JButton getButtonSaveUser() {
		return buttonSaveUser;
	}
	
}
