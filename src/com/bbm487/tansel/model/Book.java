package com.bbm487.tansel.model;

public class Book {

	private int barcode;
	private String name;
	private String author;
	private String information;
	private int available;
	
	public Book() {
	}

	public Book(int barcode, String name, String author, String information, int available) {
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
	
	public int getAvailable() {
		return available;
	}
	
	public void setAvailable(int available) {
		this.available = available;
	}
}
