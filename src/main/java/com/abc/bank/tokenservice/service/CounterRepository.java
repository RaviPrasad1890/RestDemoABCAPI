package com.abc.bank.tokenservice.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.abc.bank.tokenservice.model.Counter;

public interface CounterRepository extends CrudRepository<Counter, Integer> {
	List<Counter> findByPrimaryCounter(boolean primaryCounter);
	////tokenNumer
	Counter findByTokenNumber(int tokenNumber);

}
