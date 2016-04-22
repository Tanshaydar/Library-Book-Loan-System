package com.bbm487.tansel.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bbm487.tansel.model.Book;
import com.bbm487.tansel.model.User;
import com.google.inject.Inject;

public class SqlExecutions {

	private Connection connection;
	
	@Inject
	public SqlExecutions() {
		try {
			connection = DriverManager.getConnection(SqlQueries.JDBC_URL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createTables(){
		if(connection == null){
			System.err.println("Connection cannot be established!");
		}
		try {
			connection.createStatement().execute(SqlQueries.CREATE_TABLE_BOOK);
			connection.createStatement().execute(SqlQueries.CREATE_TABLE_USERS);
			
			connection.createStatement().execute(SqlQueries.CREATE_TABLE_CHECKOUT);
			connection.createStatement().execute(SqlQueries.CREATE_TABLE_FINE);
			connection.createStatement().execute(SqlQueries.CREATE_TABLE_WAITINGLIST);	

			connection.createStatement().execute(SqlQueries.CREATE_TABLE_ALTER111);
			connection.createStatement().execute(SqlQueries.CREATE_TABLE_ALTER112);
			connection.createStatement().execute(SqlQueries.CREATE_TABLE_ALTER121);
			connection.createStatement().execute(SqlQueries.CREATE_TABLE_ALTER122);
			connection.createStatement().execute(SqlQueries.CREATE_TABLE_ALTER131);
			connection.createStatement().execute(SqlQueries.CREATE_TABLE_ALTER132);
		} catch (SQLException e) {
			if( e.toString().contains("already exists")){
				return;
			}
			e.printStackTrace();
		}
	}
	
	public void addAdmin(){
		try {
			connection.createStatement().execute(SqlQueries.ADD_ADMIN);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addBooks() {
		try {
			connection.createStatement().execute(SqlQueries.ADD_BOOKS1);
			connection.createStatement().execute(SqlQueries.ADD_BOOKS2);
			connection.createStatement().execute(SqlQueries.ADD_BOOKS3);
			connection.createStatement().execute(SqlQueries.ADD_BOOKS4);
			connection.createStatement().execute(SqlQueries.ADD_BOOKS5);
			connection.createStatement().execute(SqlQueries.ADD_BOOKS6);
			connection.createStatement().execute(SqlQueries.ADD_BOOKS7);
			connection.createStatement().execute(SqlQueries.ADD_BOOKS8);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet searchBooks(String bookName, String bookAuthor, String bookInformation){
		Statement statement = null;
		String query = "SELECT barcode, name, author, information, available FROM BOOK WHERE ";
		if(bookName == null || bookName.isEmpty()){
			query += "UPPER(name) LIKE UPPER('%') ";
		} else {
			query += "UPPER(name) LIKE UPPER('%"+bookName+"%') ";
		}
		if(bookAuthor == null || bookAuthor.isEmpty()){
			query += "AND UPPER(author) LIKE UPPER('%') ";
		} else {
			query += "AND UPPER(author) LIKE UPPER('%"+bookAuthor+"%') ";
		}

		if(bookInformation == null || bookInformation.isEmpty()){
			query += "AND UPPER(information) LIKE UPPER('%') ";
		} else {
			query += "AND UPPER(information) LIKE UPPER('%"+bookInformation+"%') ";
		}
		
		try {
			statement = connection.createStatement();
			return statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ResultSet checkUser(String userName, String password) {
		Statement statement = null;
		String query = "SELECT * FROM USERS WHERE username='" + userName + "' AND password='" + password + "'";
		
		try {
			statement = connection.createStatement();
			return statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ResultSet getUserList() {
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			return statement.executeQuery(SqlQueries.GET_USER_LIST);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public boolean addUser(User user) {
		String query = "INSERT INTO USERS (username, password, role) VALUES ('" 
				+ user.getUserName() + "', '" 
				+ user.getPassword() + "', " 
				+ user.getUserRole().ordinal() + ")";
		
		return executeStatement(query);
	}

	public boolean deleteUser(User user) {
		String query = "DELETE FROM USERS WHERE user_id = " + user.getUserId();

		return executeStatement(query);
	}

	public boolean updateUser(User user) {
		String query = "UPDATE USERS SET username ='" + user.getUserName() 
		+ "', password = '" + user.getPassword() 
		+ "', role = " + user.getUserRole().ordinal()
		+ "WHERE user_id = " + user.getUserId();
		
		return executeStatement(query);
	}

	public boolean addBook(Book book) {
		String query = "INSERT INTO BOOKS (name, author, information, availability) VALUES ('"
				+ book.getName() + "', '"
				+ book.getAuthor() + "', '"
				+ book.getInformation() + "', "
				+ book.getAvailable().ordinal() + ")";
		
		return executeStatement(query);
	}

	public boolean updateBook(Book book) {
		String query = "UPDATE BOOK SET name ='" + book.getName()
		+"', author = '" + book.getAuthor()
		+"', information = '" + book.getInformation()
		+"', role = " + book.getAvailable().ordinal() + ")";
		
		return executeStatement(query);
	}
	
	public boolean deleteBook(Book book){
		String query = "DELETE FROM BOOK WHERE bardoce = " + book.getBarcode();
		
		return executeStatement(query);
	}
	
	private boolean executeStatement(String query){
		Statement statement = null;
		try {
			statement = connection.createStatement();
			return statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
