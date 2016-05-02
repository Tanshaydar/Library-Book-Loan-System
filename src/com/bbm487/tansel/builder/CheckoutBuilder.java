package com.bbm487.tansel.builder;

import java.sql.Timestamp;

import com.bbm487.tansel.model.Checkout;

public class CheckoutBuilder {
	private int new_checkout_id;
	private int new_user_id;
	private int new_book_id;
	private Timestamp new_checkout_date;
	private Timestamp new_return_date;
	
	public CheckoutBuilder() {
	}
	
	public CheckoutBuilder setCheckoutId(int checkoutId){
		this.new_checkout_id = checkoutId;
		return this;
	}
	
	public CheckoutBuilder setUserId(int userId){
		this.new_user_id = userId;
		return this;
	}
	
	public CheckoutBuilder setBookId(int bookId){
		this.new_book_id = bookId;
		return this;
	}
	
	public CheckoutBuilder setCheckoutDate(Timestamp checkoutDate){
		this.new_checkout_date = checkoutDate;
		return this;
	}
	
	public CheckoutBuilder setReturnDate(Timestamp returnDate){
		this.new_return_date = returnDate;
		return this;
	}
	
	public Checkout createCheckout(){
		return new Checkout(new_checkout_id, new_user_id, new_book_id, new_checkout_date, new_return_date);
	}
}
