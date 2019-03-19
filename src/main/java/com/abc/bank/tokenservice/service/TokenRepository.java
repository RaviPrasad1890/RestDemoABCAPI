package com.abc.bank.tokenservice.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.abc.bank.tokenservice.model.Token;


public interface TokenRepository extends CrudRepository<Token, Integer> {

	List<Token> findByCustomerId(int customerId);
	//tokenNumer
	
}
