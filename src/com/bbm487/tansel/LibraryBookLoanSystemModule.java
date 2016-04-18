package com.bbm487.tansel;

import com.bbm487.tansel.controller.BookController;
import com.bbm487.tansel.controller.MainController;
import com.bbm487.tansel.controller.UserController;
import com.bbm487.tansel.controller.UserListController;
import com.bbm487.tansel.factories.LoginWindowFactory;
import com.bbm487.tansel.sql.SqlExecutions;
import com.bbm487.tansel.view.BookWindow;
import com.bbm487.tansel.view.CustomerSettingsPanel;
import com.bbm487.tansel.view.LibrarianSettingsPanel;
import com.bbm487.tansel.view.LoginWindow;
import com.bbm487.tansel.view.MainWindow;
import com.bbm487.tansel.view.UserListWindow;
import com.bbm487.tansel.view.UserWindow;
import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.matcher.Matchers;
import com.google.inject.spi.InjectionListener;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

public class LibraryBookLoanSystemModule extends AbstractModule{
	
	private EventBus eventBus = new EventBus("Default EventBus");

	public static class LoginWindowProvider implements Provider<LoginWindow>{

		private LoginWindowFactory loginWindowFactory;
		private EventBus eventBus;
		
		@Inject
		public LoginWindowProvider(LoginWindowFactory loginWindowFactory, EventBus eventBus) {
			this.loginWindowFactory = loginWindowFactory;
			this.eventBus = eventBus;
		}
		
		@Override
		public LoginWindow get() {
			LoginWindow loginWindow = loginWindowFactory.create();
			eventBus.register(loginWindow);
			return loginWindow;
		}
	}
	
	@Override
	protected void configure() {
		bind(EventBus.class).toInstance(eventBus);
		bind(MainController.class);
		bind(MainWindow.class).in(Singleton.class);;
		bind(SqlExecutions.class).in(Singleton.class);
		bind(CustomerSettingsPanel.class);
		bind(LibrarianSettingsPanel.class);
		bind(UserWindow.class);
		bind(UserController.class);
		bind(BookWindow.class);
		bind(BookController.class);
		bind(UserListWindow.class);
		bind(UserListController.class);
		
		install(new FactoryModuleBuilder().build(LoginWindowFactory.class));
		
		bindListener(Matchers.any(), new TypeListener() {
            @Override
            public <I> void hear(TypeLiteral<I> type, TypeEncounter<I> encounter) {
                encounter.register(new InjectionListener<I>() {
                    @Override
                    public void afterInjection(I injectee) {
                        eventBus.register(injectee);
                    }
                });
            }
        });
	}

}
