package com.abc.bank.tokenservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class Token {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int tokenNumber;
	
	private boolean tokenActive;
	
	@OneToOne
	@JoinColumn
	private Customer customer;
	
	@OneToOne(mappedBy="token")
	private Counter counter;
	
	
	
	
	private Token() {}


	public Token(boolean tokenActive,Customer customer) {
		this.tokenActive = tokenActive;
		this.customer = customer;
	}
	
	public Token(boolean tokenActive, Counter counter) {
		this.tokenActive = tokenActive;
		this.counter = counter;
	}


	public int getTokenNumber() {
		return tokenNumber;
	}


	public void setTokenNumber(int tokenNumber) {
		this.tokenNumber = tokenNumber;
	}


	public boolean isTokenActive() {
		return tokenActive;
	}


	public void setTokenActive(boolean tokenActive) {
		this.tokenActive = tokenActive;
	}

	
	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	/*
	 * public Counter getCounter() { return counter; }
	 * 
	 * 
	 * public void setCounter(Counter counter) { this.counter = counter; }
	 */
	
	
	
}
