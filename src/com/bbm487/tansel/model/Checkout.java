package com.bbm487.tansel.model;

import java.sql.Timestamp;
import java.util.Date;

public class Checkout {

	private int checkout_id;
	private int user_id;
	private int book_id;
	private Timestamp checkout_date;
	private Timestamp return_date;
	
	public Checkout(int checkout_id, int user_id, int book_id, Timestamp checkout_date, Timestamp return_date) {
		this.checkout_id = checkout_id;
		this.user_id = user_id;
		this.book_id = book_id;
		this.checkout_date = checkout_date;
		this.return_date = return_date;
	}

	public int getCheckout_id() {
		return checkout_id;
	}

	public void setCheckout_id(int checkout_id) {
		this.checkout_id = checkout_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public Timestamp getCheckout_date() {
		return checkout_date;
	}
	
	public Date getCheckoutDate(){
		return new Date(checkout_date.getTime());
	}

	public void setCheckout_date(Timestamp checkout_date) {
		this.checkout_date = checkout_date;
	}

	public Timestamp getReturn_date() {
		return return_date;
	}
	
	public Date getReturnDate(){
		return new Date(return_date.getTime());
	}

	public void setReturn_date(Timestamp return_date) {
		this.return_date = return_date;
	}
	
	
}
