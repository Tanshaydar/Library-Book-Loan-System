package com.bbm487.tansel.view;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.bbm487.tansel.controller.LoginController;
import com.google.inject.Inject;

import net.miginfocom.swing.MigLayout;

public class LoginWindow extends JDialog {

	private JPanel contentPane;
	private JLabel labelUsername;
	private JLabel labelPassword;
	private JTextField textFieldUsername;
	private JPasswordField passwordFieldPassword;
	private JButton buttonLogin;
	private JPanel panelLogin;
	private JButton buttonCancel;
	
	private LoginController loginController;

	@Inject
	public LoginWindow(MainWindow mainWindow, LoginController loginController) {
		super(mainWindow, "Login to System");
		setModal(true);
		this.loginController = loginController;
		this.loginController.registerLoginWindow(this);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
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
		buttonLogin.addActionListener(new LoginButtonAction());
		panelLogin.add(buttonLogin, "flowx,cell 1 2,alignx right,growy");
		
		buttonCancel = new JButton("Cancel");
		buttonCancel.addActionListener(new CancelButtonAction());
		panelLogin.add(buttonCancel, "cell 1 2,alignx right,growy");
		
		pack();
	}
	
	private class LoginButtonAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String userName = textFieldUsername.getText();
			char[] password = passwordFieldPassword.getPassword();
			try {
				loginController.OnLoginButtonClicked(userName, new String(password));
			} catch (HeadlessException | SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private class CancelButtonAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			loginController.OnCancelButtonClicked();
		}
	}

}
