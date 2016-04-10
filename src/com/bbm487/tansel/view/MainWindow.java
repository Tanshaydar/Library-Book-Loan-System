package com.bbm487.tansel.view;

import javax.swing.JFrame;

import net.miginfocom.swing.MigLayout;
import javax.swing.JPanel;

import com.bbm487.tansel.LibraryBookLoanSystemModule.LoginWindowProvider;
import com.bbm487.tansel.model.BookSearchTableModel;
import com.google.inject.Inject;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

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
	private JButton buttonExit;
	private JButton buttonSearch;
	
	private JScrollPane scrollPaneSearchResults;
	
	@Inject
	public MainWindow() {		
		setTitle("Library Book Loan System - Group 16 - Tansel Altınel");
		contentPane = new JPanel();
		setContentPane(contentPane);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		contentPane.setLayout(new MigLayout("", "[10px:10px:10px][][600px:n,grow][10px:10px:10px]", "[30px:30px:30px][30px:30px:30px][30px:30px:30px][30px:30px:30px][30px:30px:30px][10px:10px:10px][300px:n,grow][10px:10px:10px]"));
		
		buttonLogin = new JButton("Login");
		contentPane.add(buttonLogin, "cell 1 0,alignx left,growy");
		
		buttonExit = new JButton("Exit");
		contentPane.add(buttonExit, "cell 2 0,alignx right,growy");
		
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
		contentPane.add(buttonSearch, "cell 1 4 2 1,grow");
		
		scrollPaneSearchResults = new JScrollPane();
		contentPane.add(scrollPaneSearchResults, "cell 1 6 2 1,grow");
		
		bookSearchTableModel = new BookSearchTableModel();
		searchResultTable = new JTable(bookSearchTableModel);
		searchResultTable.setRowHeight(30);
		scrollPaneSearchResults.setViewportView(searchResultTable);		
		
		pack();
		setLocationRelativeTo(null);
	}
	
	public void addActionListenerExitButton(ActionListener listener){
		buttonExit.addActionListener(listener);
	}
	
	public void addActionListenerLoginButton(ActionListener listener){
		buttonLogin.addActionListener(listener);
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

	public JButton getButtonExit() {
		return buttonExit;
	}

	public JButton getButtonSearch() {
		return buttonSearch;
	}

	public JScrollPane getScrollPaneSearchResults() {
		return scrollPaneSearchResults;
	}
}
