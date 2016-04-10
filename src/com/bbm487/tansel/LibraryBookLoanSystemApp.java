package com.bbm487.tansel;

import javax.swing.SwingUtilities;

import com.bbm487.tansel.controller.MainController;
import com.bbm487.tansel.sql.SqlExecutions;
import com.bbm487.tansel.sql.SqlQueries;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class LibraryBookLoanSystemApp {
	
	private static MainController mainController;

	public static void main(String[] args) {
		try {
			Class.forName(SqlQueries.JAVA_DB_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Injector injector = Guice.createInjector(new LibraryBookLoanSystemModule());
		mainController = injector.getInstance(MainController.class);
		
		SqlExecutions executions = injector.getInstance(SqlExecutions.class);
//		executions.createTables();
//		executions.addAdmin();
//		executions.addBooks();
		
		createAndShowUi();
	}
	
	private static void createAndShowUi(){
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				mainController.showMainWindow();
			}
		});
	}
	
}
