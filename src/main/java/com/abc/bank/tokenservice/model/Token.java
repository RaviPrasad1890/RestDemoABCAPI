package com.abc.bank.tokenservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Token {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int tokenNumber;
	private boolean isActive;
	private int customerId;
	private boolean isPrimaryToken;
	
	private Token() {}


	public Token(boolean isActive, int customerId,boolean isPrimaryToken) {		
		this.isActive = isActive;
		this.customerId = customerId;
		this.isPrimaryToken = isPrimaryToken;
	}




	public int getTokenNumber() {
		return tokenNumber;
	}

	public void setTokenNumber(int tokenNumber) {
		this.tokenNumber = tokenNumber;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public boolean isPrimaryToken() {
		return isPrimaryToken;
	}


	public void setPrimaryToken(boolean isPrimaryToken) {
		this.isPrimaryToken = isPrimaryToken;
	}
	
	
	
	
}
