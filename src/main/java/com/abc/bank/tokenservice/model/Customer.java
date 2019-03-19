package com.abc.bank.tokenservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int customerId;
	private String customerName;
	private boolean isPrimaryCustomer;
	
	private Customer() {
		
	}

	private Customer(int customerId, String customerName, boolean isPrimaryCustomer) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.isPrimaryCustomer = isPrimaryCustomer;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public boolean isPrimaryCustomer() {
		return isPrimaryCustomer;
	}

	public void setPrimaryCustomer(boolean isPrimaryCustomer) {
		this.isPrimaryCustomer = isPrimaryCustomer;
	}
	
	
	
	
}
