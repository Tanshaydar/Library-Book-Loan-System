package com.bbm487.tansel.model;

import java.util.Comparator;

import com.bbm487.tansel.sql.EnumValues.BOOK_AVAILABILITY;

public class Book implements Comparable<Book>, Comparator<Book>{

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

	@Override
	public int compare(Book book1, Book book2) {
		return book1.getBarcode() - book2.getBarcode();
	}

	@Override
	public int compareTo(Book book) {
		return barcode - book.getBarcode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return ((Book)obj).getBarcode() == getBarcode();
	}
}
