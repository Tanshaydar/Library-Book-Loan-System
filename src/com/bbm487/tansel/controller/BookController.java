package com.bbm487.tansel.controller;

import javax.swing.JFrame;

import com.bbm487.tansel.view.BookWindow;
import com.google.inject.Inject;

public class BookController {

	private BookWindow bookWindow;

	@Inject
	public BookController(BookWindow bookWindow) {
		this.bookWindow = bookWindow;
	}

	public void showWindow(JFrame parent) {
		bookWindow.setLocationRelativeTo(parent);
		bookWindow.setVisible(true);
	}
	
}
