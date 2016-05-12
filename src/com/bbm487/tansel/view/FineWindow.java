package com.bbm487.tansel.view;

import java.util.List;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.bbm487.tansel.model.Fine;
import com.bbm487.tansel.model.FineTableModel;
import com.bbm487.tansel.model.FinesEvent;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;

import net.miginfocom.swing.MigLayout;

public class FineWindow extends JDialog{

	private FineTableModel fineTableModel;
	private JTable fineTable;
	
	@Inject
	public FineWindow() {
		setModal(true);
		
		setTitle("My Fines");
		setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 0,grow");
		
		fineTableModel = new FineTableModel();
		fineTable = new JTable(fineTableModel);
		fineTable.setFillsViewportHeight(true);
		scrollPane.setViewportView(fineTable);
	}
	
	public void fillTable(List<Fine> fines){
		fineTableModel.clearTable();
		for (Fine fine : fines) {
			fineTableModel.addFine(fine);
		}
	}
	
	public JTable getFineTable() {
		return fineTable;
	}
	
	public FineTableModel getFineTableModel() {
		return fineTableModel;
	}
	
	@Subscribe
	public void handleFinesEvent(FinesEvent event){
		fillTable(event.getFines());
	}
	
}
