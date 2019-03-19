package com.abc.bank.tokenservice.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.abc.bank.tokenservice.model.Counter;
import com.abc.bank.tokenservice.model.Customer;
import com.abc.bank.tokenservice.model.Token;
import com.abc.bank.tokenservice.service.CounterRepository;
import com.abc.bank.tokenservice.service.CustomerRepository;
import com.abc.bank.tokenservice.service.TokenRepository;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CounterRepository counterRepository;
	
	@Autowired
	TokenRepository tokenRepository;
	
	@GetMapping(path="/customer")
	public List<Customer> getAllCustomers() {
		return (List<Customer>) customerRepository.findAll();
	}
	
	@GetMapping(path="/customer/{id}")
	public Customer getCustomer(@PathVariable int id) {
		Customer customer=null;
		customer= customerRepository.findOne(id);
		return customer;
	}
	
	@PostMapping(path="/customer")
	public ResponseEntity<Void> addCustomer(@RequestBody Customer customer){
		Customer savedCustomer= customerRepository.save(customer);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedCustomer.getCustomerId()).toUri();
		return ResponseEntity.created(location).build();
	}
	

	@GetMapping(path="/customer/token")
	public List<Token> getAllToken(){
		return (List<Token>) tokenRepository.findAll();
	}
	
	@PostMapping(path="/customer/{customerId}/token")
	public Token generateToken(@PathVariable int customerId) {
		Customer cust=customerRepository.findOne(customerId);
		Token tokenGenerated=null;
		if(cust==null) {
			return tokenGenerated;
		}
		List<Token> listOfTokensAssignedToCustomer= tokenRepository.findByCustomerId(customerId);
		//If active token exists, then return active token details
		for (Token token : listOfTokensAssignedToCustomer) {
			if(token.isActive()) {
				return token;
			}
		}
		boolean primaryToken=cust.isPrimaryCustomer();
		tokenGenerated=new Token(true,customerId,primaryToken);		
		tokenGenerated=tokenRepository.save(tokenGenerated);
		return tokenGenerated;
	}
	
	@GetMapping(path="/customer/{customerId}/token/{tokenId}")
	public Counter getTokenCounter(@PathVariable int customerId,@PathVariable int tokenId) {
		Token token = tokenRepository.findOne(tokenId);
		Counter counter=null;
		if(token!=null) {
			 Counter assignedCounter=counterRepository.findByTokenNumber(tokenId);
			
			if(assignedCounter!=null) {
				counter=assignedCounter;
			}
		}
		return counter;
		
	}
		
	@PutMapping(path="/customer/{customerId}/token/{tokenId}/counter")
	public Counter assignCounterToToken(@PathVariable int customerId,@PathVariable int tokenId) {
		Counter counter=null;
		Customer customer=customerRepository.findOne(customerId);
		if(tokenRepository.findOne(tokenId)==null) {
			return counter;
		}
		
		if(customer==null) {
			return counter;
		}
		if(tokenRepository.findOne(tokenId).getCustomerId()!=customerId) {
			return counter;
		}
		
		if(customer.isPrimaryCustomer()) {
			List<Counter> availableCountersForPrimaryCust=counterRepository.
					findByPrimaryCounter(true);
			for(Counter coun:availableCountersForPrimaryCust) {
				int tokenNumber=coun.getTokenNumber();
				Token token=tokenRepository.findOne(tokenNumber);
				if(token==null||(!(token.isActive()))) {
					coun.setTokenNumer(tokenId);
					coun.setTokenActive(true);
					counter=coun;
					break;
				}
			}
		}else {
			List<Counter> availableCountersForSecondaryCust=counterRepository.
					findByPrimaryCounter(false);
			for(Counter coun:availableCountersForSecondaryCust) {
				int tokenNumber=coun.getTokenNumber();
				Token token=tokenRepository.findOne(tokenNumber);
				if(token==null||(!(token.isActive()))) {
					coun.setTokenNumer(tokenId);
					coun.setTokenActive(true);
					counter=coun;
					break;
				}
			}
		}
		if(counter!=null) {
		 return counterRepository.save(counter);
		}else {
			//All counters are occupied, please wait
			return counter;
		}
	}

	@GetMapping(path="/customer/counter")
	public List<Counter> getAllCounters() {
		
		return (List<Counter>) counterRepository.findAll();
	}
		
	@PutMapping(path="/customer/token/counter/{counterId}")
	public Counter markTokenCompleteOfCounter(@PathVariable int counterId) {
		Counter counter=counterRepository.findOne(counterId);
		if(counter==null) {
			return null;
		}
		
		int tokenNumber=counter.getTokenNumber();
		Token token=tokenRepository.findOne(tokenNumber);
		token.setActive(false);
		tokenRepository.save(token);
		
		counter.setTokenActive(false);
		return counterRepository.save(counter);		
	}
	
}
