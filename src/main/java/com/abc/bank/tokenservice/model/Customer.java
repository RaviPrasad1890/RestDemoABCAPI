package com.abc.bank.tokenservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int customerId;
	
	private String customerName;
	
	private boolean primaryCustomer;
	
	@OneToOne(mappedBy="customer")
	private Token token;
	
	
	private Customer() {}


	public Customer(String customerName, boolean primaryCustomer) {
		this.customerName = customerName;
		this.primaryCustomer = primaryCustomer;
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
		return primaryCustomer;
	}


	public void setPrimaryCustomer(boolean primaryCustomer) {
		this.primaryCustomer = primaryCustomer;
	}

	




	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", primaryCustomer="
				+ primaryCustomer + "]";
	}
	
	
	
	
}
