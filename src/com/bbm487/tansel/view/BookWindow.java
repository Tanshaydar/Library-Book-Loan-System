package com.bbm487.tansel.view;

import javax.swing.JDialog;

import com.google.inject.Inject;

public class BookWindow extends JDialog{

	@Inject
	public BookWindow() {
		setModal(true);
	}
	
}
