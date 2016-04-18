package com.bbm487.tansel.sql;

import com.bbm487.tansel.sql.EnumValues.BOOK_AVAILABILITY;
import com.bbm487.tansel.sql.EnumValues.USER_ROLE;

public class SqlQueries {

	public static final String JAVA_DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	public static final String JDBC_URL = "jdbc:derby:lblsdb;create=true;collation=TERRITORY_BASED";
	
	public static final String ADD_ADMIN = "INSERT INTO USERS (username, password, role) VALUES ('admin', 'admin123', " + USER_ROLE.LIBRARIAN.ordinal() + ")";
	public static final String ADD_BOOKS1 = "INSERT INTO BOOK (name, author, information, available) VALUES ('The Gunslinger', 'Stephen King', '224 Pages, First Book of The Dark Tower series', " + BOOK_AVAILABILITY.AVAILABLE.ordinal() + ")";
	public static final String ADD_BOOKS2 = "INSERT INTO BOOK (name, author, information, available) VALUES ('The Drawing of the Three', 'Stephen King', '400 Pages, Second Book of The Dark Tower series', " + BOOK_AVAILABILITY.UNAVAILABLE.ordinal() + ")";
	public static final String ADD_BOOKS3 = "INSERT INTO BOOK (name, author, information, available) VALUES ('The Wastelands', 'Stephen King', '512 Pages, Third Book of The Dark Tower series', " + BOOK_AVAILABILITY.AVAILABLE.ordinal() + ")";
	public static final String ADD_BOOKS4 = "INSERT INTO BOOK (name, author, information, available) VALUES ('Wizard and Glass', 'Stephen King', '787 Pages, Fourth Book of The Dark Tower series', " + BOOK_AVAILABILITY.UNAVAILABLE.ordinal() + ")";
	public static final String ADD_BOOKS5 = "INSERT INTO BOOK (name, author, information, available) VALUES ('Wolves of the Calla', 'Stephen King', '714 Pages, Fifth Book of The Dark Tower series', " + BOOK_AVAILABILITY.AVAILABLE.ordinal() + ")";
	public static final String ADD_BOOKS6 = "INSERT INTO BOOK (name, author, information, available) VALUES ('Song of Susannah', 'Stephen King', '432 Pages, Fifth Book of The Dark Tower series', " + BOOK_AVAILABILITY.UNAVAILABLE.ordinal() + ")";
	public static final String ADD_BOOKS7 = "INSERT INTO BOOK (name, author, information, available) VALUES ('The Dark Tower', 'Stephen King', '845 Pages, Seventh and the final Book of The Dark Tower series', " + BOOK_AVAILABILITY.AVAILABLE.ordinal() + ")";
	public static final String ADD_BOOKS8 = "INSERT INTO BOOK (name, author, information, available) VALUES ('The Wind Through the Keyhole', 'Stephen King', '336 Pages, between fourth and fifth books of The Dark Tower series', " + BOOK_AVAILABILITY.AVAILABLE.ordinal() + ")";
	
	public static final String CREATE_TABLE_BOOK = "CREATE TABLE BOOK (barcode INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1000, INCREMENT BY 1)," +
							"name varchar(100) NOT NULL," +
							"author varchar(100) NOT NULL," +
							"information varchar(1000) NOT NULL," +
							"available INT NOT NULL )";
	
	public static final String CREATE_TABLE_USERS = "CREATE TABLE USERS ( user_id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1000, INCREMENT BY 1)," +
							"username varchar(25) NOT NULL," +
							"password varchar(25) NOT NULL," +
							"role INT NOT NULL )";

	public static final String CREATE_TABLE_CHECKOUT = "CREATE TABLE CHECKOUT (checkout_id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1000, INCREMENT BY 1)," +
							"user_id INT NOT NULL," +
							"book_id INT NOT NULL," +
							"checkout_date TIMESTAMP NOT NULL," +
							"return_date TIMESTAMP DEFAULT NULL )";
							
	public static final String CREATE_TABLE_FINE = "CREATE TABLE FINE ( fine_id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1000, INCREMENT BY 1)," +
							"user_id INT NOT NULL," +
							"book_id INT NOT NULL," +
							"cost INT NOT NULL )";							
	
								
	public static final String CREATE_TABLE_WAITINGLIST= "CREATE TABLE WAITINGLIST ( waitinglist_id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1000, INCREMENT BY 1)," +
							"user_id INT NOT NULL," +
							"book_id INT NOT NULL )";

	public static final String CREATE_TABLE_ALTER111 = "ALTER TABLE CHECKOUT ADD CONSTRAINT CHECKOUT_ibfk_1 FOREIGN KEY (book_id) REFERENCES BOOK (barcode) ON DELETE CASCADE ON UPDATE RESTRICT";
	public static final String CREATE_TABLE_ALTER112 = "ALTER TABLE CHECKOUT ADD CONSTRAINT CHECKOUT_ibfk_2 FOREIGN KEY (user_id) REFERENCES USERS (user_id) ON DELETE CASCADE ON UPDATE RESTRICT ";
								
	public static final String CREATE_TABLE_ALTER121 = "ALTER TABLE FINE ADD CONSTRAINT FINE_ibfk_2 FOREIGN KEY (book_id) REFERENCES BOOK (barcode) ON DELETE CASCADE ON UPDATE RESTRICT ";
	public static final String CREATE_TABLE_ALTER122 = "ALTER TABLE FINE ADD CONSTRAINT FINE_ibfk_1 FOREIGN KEY (user_id) REFERENCES USERS (user_id) ON DELETE CASCADE ON UPDATE RESTRICT ";
	
	public static final String CREATE_TABLE_ALTER131 = "ALTER TABLE WAITINGLIST ADD CONSTRAINT WAITINGLIST_ibfk_2 FOREIGN KEY (book_id) REFERENCES BOOK (barcode) ON DELETE CASCADE ON UPDATE RESTRICT";
	public static final String CREATE_TABLE_ALTER132 = "ALTER TABLE WAITINGLIST ADD CONSTRAINT WAITINGLIST_ibfk_1 FOREIGN KEY (user_id) REFERENCES USERS (user_id) ON DELETE CASCADE ON UPDATE RESTRICT ";
	
	public static final String GET_USER_LIST = "SELECT * FROM USERS";
	
	}
