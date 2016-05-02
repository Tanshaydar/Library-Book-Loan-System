package com.bbm487.tansel.builder;

import com.bbm487.tansel.model.Book;
import com.bbm487.tansel.sql.EnumValues.BOOK_AVAILABILITY;

public class BookBuilder {
	private int new_barcode;
	private String new_name;
	private String new_author;
	private String new_information;
	private BOOK_AVAILABILITY new_available;
	
	public BookBuilder() {
	}
	
	public BookBuilder setBarcode(int barcode){
		this.new_barcode = barcode;
		return this;
	}
	
	public BookBuilder setName(String name){
		this.new_name = name;
		return this;
	}
	
	public BookBuilder setAuthor(String author){
		this.new_author = author;
		return this;
	}
	
	public BookBuilder setInformation(String information){
		this.new_information = information;
		return this;
	}
	
	public BookBuilder setAvailability(BOOK_AVAILABILITY available){
		this.new_available = available;
		return this;
	}
	
	public Book createBook(){
		return new Book(new_barcode, new_name, new_author, new_information, new_available);
	}
}
