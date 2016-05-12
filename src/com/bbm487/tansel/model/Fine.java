package com.bbm487.tansel.model;

public class Fine {

	private Book book;
	private int daysLate;
	private int cost;
	
	public Fine(Book book, int daysLate, int cost) {
		this.book = book;
		this.daysLate = daysLate;
		this.cost = cost;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getDaysLate() {
		return daysLate;
	}

	public void setDaysLate(int daysLate) {
		this.daysLate = daysLate;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
}
