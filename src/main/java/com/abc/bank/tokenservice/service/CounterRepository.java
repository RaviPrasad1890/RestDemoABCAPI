package com.abc.bank.tokenservice.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abc.bank.tokenservice.model.Counter;
import com.abc.bank.tokenservice.model.Token;

@Repository
public interface CounterRepository extends CrudRepository<Counter, Integer> {
	
	List<Counter> findByPrimaryCounter(boolean primaryCounter);
}
