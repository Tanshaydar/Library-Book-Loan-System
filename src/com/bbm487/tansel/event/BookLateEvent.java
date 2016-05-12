package com.bbm487.tansel.event;

import com.bbm487.tansel.model.Book;

public class BookLateEvent {

	private Book book;
	
	public BookLateEvent(Book book) {
		this.book = book;
	}
	
	public Book getBook() {
		return book;
	}
	
}
