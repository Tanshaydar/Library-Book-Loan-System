package com.bbm487.tansel.view;

import javax.swing.JDialog;

import com.google.inject.Inject;

public class ReturnBookWindow extends JDialog{

	@Inject
	public ReturnBookWindow(MainWindow mainWindow) {
		super(mainWindow, "Return Book");
		setModal(true);
	}
	
}
