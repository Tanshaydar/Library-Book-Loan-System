package com.bbm487.tansel.view;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.bbm487.tansel.model.Book;
import com.bbm487.tansel.sql.EnumValues.BOOK_AVAILABILITY;
import com.bbm487.tansel.sql.EnumValues.USER_ROLE;
import com.google.inject.Inject;

import net.miginfocom.swing.MigLayout;

public class BookWindow extends JDialog{
	private JLabel labelBookName;
	private JLabel labelBookAuthor;
	private JLabel labelBookInformation;
	private JLabel labelBookAvailability;
	private JLabel labelBookBarcode;
	
	private JScrollPane scrollPane;
	private JTextArea textAreaBookInformation;
	private JTextField textFieldBookBarcode;
	private JTextField textFieldBookAuthor;
	private JTextField textFieldBookName;
	private JComboBox<BOOK_AVAILABILITY> comboBoxBookAvailability;
	
	private JPanel panel;
	private JButton buttonSaveBook;
	private JButton buttonEditBook;
	private JButton buttonDeleteBook;
	private JButton buttonCheckout;

	private Book book;

	@Inject
	public BookWindow() {
		setModal(true);
		
		setTitle("Book Management");
		
		getContentPane().setLayout(new MigLayout("", "[][300px:n,grow]", "[][30][30][100px:n,grow][30][]"));
		
		labelBookBarcode = new JLabel("Barcode:");
		getContentPane().add(labelBookBarcode, "cell 0 0,alignx right,aligny center,hidemode 3");
		
		textFieldBookBarcode = new JTextField();
		textFieldBookBarcode.setEditable(false);
		getContentPane().add(textFieldBookBarcode, "cell 1 0,grow,hidemode 3");
		textFieldBookBarcode.setColumns(10);
		
		labelBookName = new JLabel("Book Name:");
		getContentPane().add(labelBookName, "cell 0 1,alignx right,aligny center");
		
		textFieldBookName = new JTextField();
		getContentPane().add(textFieldBookName, "cell 1 1,grow");
		textFieldBookName.setColumns(10);
		
		labelBookAuthor = new JLabel("Book Author:");
		getContentPane().add(labelBookAuthor, "cell 0 2,alignx right,aligny center");
		
		textFieldBookAuthor = new JTextField();
		getContentPane().add(textFieldBookAuthor, "cell 1 2,grow");
		
		labelBookInformation = new JLabel("Book Information:");
		getContentPane().add(labelBookInformation, "cell 0 3,alignx right,aligny top");
		
		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "cell 1 3,grow");
		
		textAreaBookInformation = new JTextArea();
		textAreaBookInformation.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textAreaBookInformation.setLineWrap(true);
		textAreaBookInformation.setWrapStyleWord(true);
		scrollPane.setViewportView(textAreaBookInformation);
		
		labelBookAvailability = new JLabel("Book Availability:");
		getContentPane().add(labelBookAvailability, "cell 0 4,alignx right,aligny center");
		
		comboBoxBookAvailability = new JComboBox<BOOK_AVAILABILITY>();
		comboBoxBookAvailability.addItem(BOOK_AVAILABILITY.AVAILABLE);
		comboBoxBookAvailability.addItem(BOOK_AVAILABILITY.UNAVAILABLE);
		getContentPane().add(comboBoxBookAvailability, "cell 1 4,grow");
		
		panel = new JPanel();
		getContentPane().add(panel, "cell 0 5 2 1,alignx center,growy");
		panel.setLayout(new MigLayout("", "[][][][]", "[]"));
		
		buttonSaveBook = new JButton("Save Book");
		panel.add(buttonSaveBook, "cell 0 0,alignx center,growy,hidemode 3");
		
		buttonEditBook = new JButton("Edit Book");
		panel.add(buttonEditBook, "cell 1 0,alignx center,growy,hidemode 3");
		
		buttonDeleteBook = new JButton("Delete Book");
		panel.add(buttonDeleteBook, "cell 2 0,alignx center,growy,hidemode 3");
		
		buttonCheckout = new JButton("Checkout");
		panel.add(buttonCheckout, "cell 3 0,alignx center,growy,hidemode 3");
		
		pack();
	}
	
	public void addActionListenerToSaveButton(ActionListener listener){
		buttonSaveBook.addActionListener(listener);
	}
	public void addActionListenerToEditButton(ActionListener listener){
		buttonEditBook.addActionListener(listener);
	}
	public void addActionListenerToDeleteButton(ActionListener listener){
		buttonDeleteBook.addActionListener(listener);
	}
	public void addActionListenerToCheckoutButton(ActionListener listener){
		buttonCheckout.addActionListener(listener);
	}

	public void setBook(Book book) {
		this.book = book;
		textFieldBookBarcode.setText(Integer.toString(book.getBarcode()));
		textFieldBookName.setText(book.getName());
		textFieldBookAuthor.setText(book.getAuthor());
		textAreaBookInformation.setText(book.getInformation());
		comboBoxBookAvailability.setSelectedItem(book.getAvailable());
	}
	
	public Book getBook() {
		if(book == null) {
			
			Book book = new Book();
			book.setBarcode(-1);
			book.setName(textFieldBookName.getText());
			book.setAuthor(textFieldBookAuthor.getText());
			book.setInformation(textAreaBookInformation.getText());
			book.setAvailable((BOOK_AVAILABILITY) comboBoxBookAvailability.getSelectedItem());
			
			if(textFieldBookName.getText() == null || textFieldBookName.getText().isEmpty()
					|| textFieldBookAuthor.getText() == null || textFieldBookAuthor.getText().isEmpty()
					|| textAreaBookInformation.getText() == null || textAreaBookInformation.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "You need to fill the fields!", "Error!", JOptionPane.ERROR_MESSAGE);
				return null;
			}
			
			return book;
			
		} else {
			book.setName(textFieldBookName.getText());
			book.setAuthor(textFieldBookAuthor.getText());
			book.setInformation(textAreaBookInformation.getText());
			book.setAvailable((BOOK_AVAILABILITY) comboBoxBookAvailability.getSelectedItem());
			return book;
		}
	}
	
	public void clean() {
		book = null;
		textFieldBookBarcode.setText(null);
		textFieldBookName.setText(null);
		textFieldBookAuthor.setText(null);
		textAreaBookInformation.setText(null);
		comboBoxBookAvailability.setSelectedItem(BOOK_AVAILABILITY.AVAILABLE);
		buttonEditBook.setVisible(false);
		buttonDeleteBook.setVisible(false);
		buttonSaveBook.setVisible(false);
		buttonCheckout.setVisible(false);
	}
	
	public void setEditable(boolean editable, USER_ROLE role){
		textFieldBookName.setEditable(editable);
		textFieldBookAuthor.setEditable(editable);
		textAreaBookInformation.setEditable(editable);
		comboBoxBookAvailability.setEnabled(editable);
		if(role == USER_ROLE.CUSTOMER) {
			buttonEditBook.setVisible(false);
			buttonDeleteBook.setVisible(false);
			buttonSaveBook.setVisible(false);
			buttonCheckout.setVisible(true);
		} else if(role == USER_ROLE.LIBRARIAN) {
			if(book == null) {
				buttonDeleteBook.setVisible(editable);
			} else {
				buttonDeleteBook.setVisible(false);
			}
			buttonSaveBook.setVisible(editable);
			buttonEditBook.setVisible(!editable);
		}
	}
	
}
