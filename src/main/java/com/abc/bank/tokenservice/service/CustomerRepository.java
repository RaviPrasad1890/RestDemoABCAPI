package com.abc.bank.tokenservice.service;

import org.springframework.data.repository.CrudRepository;

import com.abc.bank.tokenservice.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
