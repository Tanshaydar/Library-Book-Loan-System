package com.bbm487.tansel.view;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.bbm487.tansel.model.BookSearchTableModel;
import com.bbm487.tansel.model.User;
import com.google.inject.Inject;

import net.miginfocom.swing.MigLayout;
import java.awt.Color;

public class MainWindow extends JFrame{

	private JLabel labelBookName;
	private JLabel labelBookAuthor;
	private JLabel labelBookInfo;
	
	private JPanel contentPane;
	private BookSearchTableModel bookSearchTableModel;
	private JTable searchResultTable;
	private JTextField textFieldBookName;
	private JTextField textFieldBookAuthor;
	private JTextField textFieldInformation;
	
	private JButton buttonLogin;
	private JButton buttonLogout;
	private JButton buttonSearch;
	
	private JScrollPane scrollPaneSearchResults;
	
	private CustomerSettingsPanel customerSettingsPanel;
	private LibrarianSettingsPanel librarianSettingsPanel;
	private JLabel labelNotificationLabel;
	
	@Inject
	public MainWindow(CustomerSettingsPanel customerSettingsPanel,
			LibrarianSettingsPanel librarianSettingsPanel) {

		this.customerSettingsPanel = customerSettingsPanel;
		this.librarianSettingsPanel = librarianSettingsPanel;
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle("Library Book Loan System - Group 16 - Tansel Altınel");
		contentPane = new JPanel();
		setContentPane(contentPane);
		
		contentPane.setLayout(new MigLayout("", "[10px:10px:10px][][600px:n,grow][grow][10px:10px:10px]", "[35px:35px:35px,grow][30px:30px:30px][30px:30px:30px][30px:30px:30px][10px:10px:10px][300px:n,grow][15px:15px:15px]"));
		
		buttonLogin = new JButton("Login");
		contentPane.add(buttonLogin, "hidemode 3,cell 1 0,alignx left,aligny center");
		
		buttonLogout = new JButton("Logout");
		buttonLogout.setVisible(false);
		contentPane.add(buttonLogout,  "hidemode 3,cell 1 0,alignx left,aligny center");
		
		contentPane.add(customerSettingsPanel, "cell 2 0 2 1,growx,aligny center,hidemode 3");
		customerSettingsPanel.setVisible(false);
		contentPane.add(librarianSettingsPanel, "cell 2 0 2 1,growx,aligny center,hidemode 3");
		librarianSettingsPanel.setVisible(false);
		
		labelBookName = new JLabel("Book Name:");
		contentPane.add(labelBookName, "cell 1 1,alignx left,aligny center");
		
		textFieldBookName = new JTextField();
		contentPane.add(textFieldBookName, "cell 2 1,grow");
		textFieldBookName.setColumns(10);
		
		labelBookAuthor = new JLabel("Book Author:");
		contentPane.add(labelBookAuthor, "cell 1 2,alignx left,aligny center");
		
		textFieldBookAuthor = new JTextField();
		contentPane.add(textFieldBookAuthor, "cell 2 2,grow");
		textFieldBookAuthor.setColumns(10);
		
		labelBookInfo = new JLabel("Information:");
		contentPane.add(labelBookInfo, "cell 1 3,alignx left,aligny center");
		
		textFieldInformation = new JTextField();
		contentPane.add(textFieldInformation, "cell 2 3,grow");
		textFieldInformation.setColumns(10);
		
		buttonSearch = new JButton("Search");
		contentPane.add(buttonSearch, "cell 3 1 1 3,grow");
		
		scrollPaneSearchResults = new JScrollPane();
		contentPane.add(scrollPaneSearchResults, "cell 1 5 3 1,grow");
		
		bookSearchTableModel = new BookSearchTableModel();
		searchResultTable = new JTable(bookSearchTableModel);
		searchResultTable.setFillsViewportHeight(true);
		searchResultTable.setRowHeight(30);
		scrollPaneSearchResults.setViewportView(searchResultTable);		
		
		labelNotificationLabel = new JLabel("");
		labelNotificationLabel.setForeground(Color.RED);
		contentPane.add(labelNotificationLabel, "cell 1 6 3 1");
		
		pack();
		setLocationRelativeTo(null);
	}
	
	public void enableLoginUiChanges(User user){
		getButtonLogin().setVisible(false);
		getButtonLogout().setVisible(true);
		switch (user.getUserRole()) {
		case CUSTOMER:
			customerSettingsPanel.setVisible(true);
			break;
		case LIBRARIAN:
			librarianSettingsPanel.setVisible(true);
			break;
		default:
			break;
		}
	}
	
	public void enableLogoutUiChanges(){
		getButtonLogin().setVisible(true);
		getButtonLogout().setVisible(false);
		customerSettingsPanel.setVisible(false);
		librarianSettingsPanel.setVisible(false);
	}
	
	public CustomerSettingsPanel getCustomerSettingsPanel() {
		return customerSettingsPanel;
	}
	
	public LibrarianSettingsPanel getLibrarianSettingsPanel() {
		return librarianSettingsPanel;
	}
	
	public void addActionListenerLoginButton(ActionListener listener){
		buttonLogin.addActionListener(listener);
	}
	
	public void addActionlistenerLogoutButton(ActionListener listener){
		buttonLogout.addActionListener(listener);
	}
	
	public void addActionlistenerToSearchButton(ActionListener listener){
		buttonSearch.addActionListener(listener);
	}

	public JLabel getLabelBookName() {
		return labelBookName;
	}

	public JLabel getLabelBookAuthor() {
		return labelBookAuthor;
	}

	public JLabel getLabelBookInfo() {
		return labelBookInfo;
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public BookSearchTableModel getBookSearchTableModel() {
		return bookSearchTableModel;
	}

	public JTable getSearchResultTable() {
		return searchResultTable;
	}

	public JTextField getTextFieldBookName() {
		return textFieldBookName;
	}

	public JTextField getTextFieldBookAuthor() {
		return textFieldBookAuthor;
	}

	public JTextField getTextFieldInformation() {
		return textFieldInformation;
	}

	public JButton getButtonLogin() {
		return buttonLogin;
	}
	
	public JButton getButtonLogout() {
		return buttonLogout;
	}

	public JButton getButtonSearch() {
		return buttonSearch;
	}

	public JScrollPane getScrollPaneSearchResults() {
		return scrollPaneSearchResults;
	}
	
	public JLabel getLabelNotificationLabel() {
		return labelNotificationLabel;
	}
}
