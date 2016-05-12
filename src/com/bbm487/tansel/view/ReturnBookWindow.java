package com.bbm487.tansel.view;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.google.inject.Inject;

import net.miginfocom.swing.MigLayout;

public class ReturnBookWindow extends JDialog{

	private JButton buttonReturnBook;
	private JTextField textFieldBarcode;
	
	@Inject
	public ReturnBookWindow(MainWindow mainWindow) {
		super(mainWindow, "Return Book");
		
		setModal(true);
		getContentPane().setLayout(new MigLayout("", "[][grow]", "[][][grow]"));

		JLabel lblBarcode = new JLabel("Barcode:");
		getContentPane().add(lblBarcode, "cell 0 0,alignx right,growy");
		
		textFieldBarcode = new JTextField();
		getContentPane().add(textFieldBarcode, "cell 1 0,grow");
		textFieldBarcode.setColumns(10);
		
		buttonReturnBook = new JButton("Return Book");
		getContentPane().add(buttonReturnBook, "cell 1 1,alignx right,growy");
	}
	
	public void addActionListenerToButtonReturnBook(ActionListener listener){
		buttonReturnBook.addActionListener(listener);
	}
	
	public JButton getButtonReturnBook() {
		return buttonReturnBook;
	}
	
	public JTextField getTextFieldBarcode() {
		return textFieldBarcode;
	}
}
