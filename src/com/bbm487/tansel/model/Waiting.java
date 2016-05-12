package com.bbm487.tansel.model;

public class Waiting {
	private int waitinglist_id;
	private int user_id;
	private int book_id;

	public Waiting(int waitinglist_id, int user_id, int book_id) {
		this.waitinglist_id = waitinglist_id;
		this.user_id = user_id;
		this.book_id = book_id;
	}

	public int getWaitinglist_id() {
		return waitinglist_id;
	}

	public void setWaitinglist_id(int waitinglist_id) {
		this.waitinglist_id = waitinglist_id;
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
	
}
