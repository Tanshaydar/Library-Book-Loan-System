package com.bbm487.tansel.controller;

import javax.swing.JFrame;

import com.bbm487.tansel.view.FineWindow;
import com.google.inject.Inject;

public class FineController {

	private FineWindow fineWindow;
	
	@Inject
	public FineController(FineWindow fineWindow) {
		this.fineWindow = fineWindow;
	}
	
	public void showWindow(JFrame parent){
		
		fineWindow.pack();
		fineWindow.setLocationRelativeTo(parent);
		fineWindow.setVisible(true);
	} 
	
}
