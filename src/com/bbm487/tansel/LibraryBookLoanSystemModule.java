package com.bbm487.tansel;

import com.bbm487.tansel.controller.MainController;
import com.bbm487.tansel.factories.LoginWindowFactory;
import com.bbm487.tansel.sql.SqlExecutions;
import com.bbm487.tansel.view.LoginWindow;
import com.bbm487.tansel.view.MainWindow;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class LibraryBookLoanSystemModule extends AbstractModule{

	public static class LoginWindowProvider implements Provider<LoginWindow>{

		private LoginWindowFactory loginWindowFactory;
		
		@Inject
		public LoginWindowProvider(LoginWindowFactory loginWindowFactory) {
			this.loginWindowFactory = loginWindowFactory;
		}
		
		@Override
		public LoginWindow get() {
			return loginWindowFactory.create();
		}
	}
	
	@Override
	protected void configure() {
		bind(MainController.class);
		bind(MainWindow.class);
		bind(SqlExecutions.class).in(Singleton.class);
		
		install(new FactoryModuleBuilder().build(LoginWindowFactory.class));
	}

}
