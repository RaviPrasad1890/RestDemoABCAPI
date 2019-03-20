package com.abc.bank.tokenservice.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Counter {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int counterNumber;
	
	private boolean primaryCounter;
	
	//private boolean tokenStatus;
		
	@OneToOne
	@JoinColumn
	private Token token;
	
	private Counter() {}

	/*
	 * public Counter(boolean tokenStatus) { this.tokenStatus = tokenStatus; }
	 */

	public Counter(boolean primaryCounter, Token token) {
	
		this.primaryCounter=primaryCounter;
		
		this.token = token;
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

	/*
	 * public boolean isTokenStatus() { return tokenStatus; }
	 * 
	 * public void setTokenStatus(boolean tokenStatus) { this.tokenStatus =
	 * tokenStatus; }
	 */

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	
	
	
	
	
}
