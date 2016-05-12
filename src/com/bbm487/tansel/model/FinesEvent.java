package com.bbm487.tansel.model;

import java.util.List;

public class FinesEvent {

	private List<Fine> fines;
	
	public FinesEvent(List<Fine> fines) {
		this.fines = fines;
	}
	
	public List<Fine> getFines() {
		return fines;
	}
	
}
