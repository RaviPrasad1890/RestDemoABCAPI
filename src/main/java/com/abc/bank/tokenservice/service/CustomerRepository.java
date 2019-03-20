package com.abc.bank.tokenservice.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abc.bank.tokenservice.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
