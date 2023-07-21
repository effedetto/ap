package com.example.ap.utility;

public enum State {
	
	 C("CREATED"), P("PROGRESS"), F("FINISHED"), E("GOAL");	


	private State(final String stato) {
		this.stato = stato; 
	}
	
	private String stato;
	
	public String getStato() {
		return stato;
	}
	
	
}
