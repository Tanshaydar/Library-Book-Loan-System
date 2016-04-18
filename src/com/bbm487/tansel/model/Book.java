package com.bbm487.tansel.model;

import com.bbm487.tansel.sql.EnumValues.BOOK_AVAILABILITY;

public class Book {

	private int barcode;
	private String name;
	private String author;
	private String information;
	private BOOK_AVAILABILITY available;
	
	public Book() {
	}

	public Book(int barcode, String name, String author, String information, BOOK_AVAILABILITY available) {
		this.barcode = barcode;
		this.name = name;
		this.author = author;
		this.information = information;
		this.available = available;
	}

	public int getBarcode() {
		return barcode;
	}

	public void setBarcode(int barcode) {
		this.barcode = barcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}
	
	public BOOK_AVAILABILITY getAvailable() {
		return available;
	}
	
	public void setAvailable(BOOK_AVAILABILITY available) {
		this.available = available;
	}
}
