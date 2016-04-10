package com.bbm487.tansel.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import com.google.inject.Inject;

public class LoginWindow extends JFrame {

	private JPanel contentPane;
	private JLabel labelUsername;
	private JLabel labelPassword;
	private JTextField textFieldUsername;
	private JPasswordField passwordFieldPassword;
	private JButton buttonLogin;
	private JPanel panelLogin;
	private JButton buttonCancel;

	@Inject
	public LoginWindow() {
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		panelLogin = new JPanel();
		panelLogin.setBorder(new TitledBorder(null, "Library Book Loan System Login", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panelLogin, "cell 0 0,grow");
		panelLogin.setLayout(new MigLayout("", "[70px:n][200px:n,grow]", "[][][]"));
		
		labelUsername = new JLabel("Username:");
		panelLogin.add(labelUsername, "cell 0 0,alignx left,aligny center");
		
		textFieldUsername = new JTextField();
		panelLogin.add(textFieldUsername, "cell 1 0,grow");
		textFieldUsername.setColumns(10);
		
		labelPassword = new JLabel("Password:");
		panelLogin.add(labelPassword, "cell 0 1,alignx left,aligny center");
		
		passwordFieldPassword = new JPasswordField();
		panelLogin.add(passwordFieldPassword, "cell 1 1,grow");
		
		buttonLogin = new JButton("Login");
		panelLogin.add(buttonLogin, "flowx,cell 1 2,alignx right,growy");
		
		buttonCancel = new JButton("Cancel");
		panelLogin.add(buttonCancel, "cell 1 2,alignx right,growy");
		
		pack();
	}

}
