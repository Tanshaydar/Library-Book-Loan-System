package com.bbm487.tansel.controller;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.bbm487.tansel.view.CheckoutListPanel;
import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;

public class CheckoutListController {

	private CheckoutListPanel checkoutListPanel;
	private EventBus eventBus;
	
	@Inject
	public CheckoutListController(CheckoutListPanel checkoutListPanel, EventBus eventBus) {
		this.checkoutListPanel = checkoutListPanel;
		this.eventBus = eventBus;
	}

	public void showWindow(JFrame parent) {
		JDialog dialog = new JDialog(parent);
		dialog.setModal(true);
		contentPane = new JPanel();
		setContentPane(contentPane);
		dialog.setLocationRelativeTo(parent);
		dialog.setVisible(true);
	}

	
	
}
