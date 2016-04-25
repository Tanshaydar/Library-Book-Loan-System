package com.bbm487.tansel.view;

import javax.swing.JPanel;

import com.google.inject.Inject;

import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;

public class CustomerSettingsPanel extends JPanel{

	private JButton buttonReturnBook;
	private JButton buttonSeeMyBookList;
	private JButton buttonSeeMyFines;
	
	@Inject
	public CustomerSettingsPanel() {
		setLayout(new MigLayout("fill, insets 0 0 0 0", "[][][]", "[]"));
		
		buttonReturnBook = new JButton("Return Book");
		add(buttonReturnBook, "cell 0 0, grow");
		
		buttonSeeMyBookList = new JButton("See My Book List");
		add(buttonSeeMyBookList, "cell 1 0,grow");
		
		buttonSeeMyFines = new JButton("See My Fines");
		add(buttonSeeMyFines, "cell 2 0,grow");
	}
	
	public JButton getButtonReturnBook() {
		return buttonReturnBook;
	}
	
	public JButton getButtonSeeMyBookList() {
		return buttonSeeMyBookList;
	}
	
	public JButton getButtonSeeMyFines() {
		return buttonSeeMyFines;
	}
	
}
