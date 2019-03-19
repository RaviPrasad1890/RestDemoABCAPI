package com.abc.bank.tokenservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Counter {

	@Id
	private int counterNumber;
	private boolean primaryCounter;
	private int tokenNumber;
	private boolean tokenActive;
	

	private Counter() {
		
	}

    
	
	private Counter(int counterNumber, boolean primaryCounter, int tokenNumber, boolean tokenActive) {
		this.counterNumber = counterNumber;
		this.primaryCounter = primaryCounter;
		this.tokenNumber = tokenNumber;
		this.tokenActive = tokenActive;
	}



	public int getCounterNumber() {
		return counterNumber;
	}


	public void setCounterNumber(int counterNumber) {
		this.counterNumber = counterNumber;
	}


	public boolean isPrimaryCounter() {
		return primaryCounter;
	}


	public void setPrimaryCounter(boolean primaryCounter) {
		this.primaryCounter = primaryCounter;
	}


	public int getTokenNumber() {
		return tokenNumber;
	}


	public void setTokenNumer(int tokenNumber) {
		this.tokenNumber = tokenNumber;
	}



	public boolean isTokenActive() {
		return tokenActive;
	}



	public void setTokenActive(boolean tokenActive) {
		this.tokenActive = tokenActive;
	}	
	
	
	
}
