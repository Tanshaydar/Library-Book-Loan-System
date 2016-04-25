package com.bbm487.tansel.event;

import com.bbm487.tansel.model.Book;

public class BookEvent {

	private Book book;
	
	public BookEvent(Book book) {
		this.book = book;
	}
	
	public Book getBook() {
		return book;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}

}
