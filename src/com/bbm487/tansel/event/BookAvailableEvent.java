package com.bbm487.tansel.event;

import com.bbm487.tansel.model.Book;

public class BookAvailableEvent {

	private Book book;

	public BookAvailableEvent(Book book) {
		this.book = book;
	}
	
	public Book getBook() {
		return book;
	}
	
}
