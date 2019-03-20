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
	 @PostMapping(path="/customer") 
	 public ResponseEntity<Void> addCustomer(@RequestBody Customer customer){ 
		 Customer savedCustomer=
				 customerRepository.save(customer); URI location = ServletUriComponentsBuilder
				 .fromCurrentRequest() .path("/{id}")
				 .buildAndExpand(savedCustomer.getCustomerId()).toUri();
				 return ResponseEntity.created(location).build(); 
	 }
	 
	  @GetMapping(path="/customer/{customerId}") 
	  public Customer getCustomer(@PathVariable int customerId) throws CustomException { 
		  Customer customer=null; 
		  customer= customerRepository.findOne(customerId);
		  if(customer!=null) {
			  return customer;
		  }else {
			  throw new CustomException("Customer Does Not Exists");
		  }
		 
	 }
	
	@GetMapping(path="/customer/token")
	public List<Token> getAllTokens() {
		  return (List<Token>) tokenRepository.findAll(); 
		}
	
	@GetMapping(path="/customer/token/counter")
	public List<Counter> getAllCounters() {
		  return (List<Counter>) counterRepository.findAll(); 
		}
	
	@PostMapping("/customer/{customerId}/token")
	public Token generateToken(@PathVariable int customerId) throws CustomException {		
		Customer customer=customerRepository.findOne(customerId);
		if(customer==null) {
			throw new CustomException("Customer Not Found");
		}
		Token token= new Token(true,customer);
		return tokenRepository.save(token);
	}
	
	@PutMapping("/customer/{customerId}/token/{tokenId}/counter")
	public Counter assignTokenToCounter(@PathVariable int customerId,@PathVariable int tokenId) throws CustomException {
		
		Counter counterFinal=null;
		
		
		Customer incomingCustomer=customerRepository.findOne(customerId);
		Token incomingToken=tokenRepository.findOne(tokenId);
		
		if(incomingCustomer==null||incomingToken==null) {
			throw new CustomException("Customer/Token not Found");
		}
		
		if(incomingToken.getCustomer().getCustomerId()!=incomingCustomer.getCustomerId()) {
			throw new CustomException("Customer Token relationship not found");
		}
		
		//Find list of all primary and non primary counters available
		List<Counter> primaryCounterList= counterRepository.findByPrimaryCounter(true);
		List<Counter> seconadryCounterList= counterRepository.findByPrimaryCounter(false);
		
		if(incomingCustomer.isPrimaryCustomer()) {
			for (Counter counter : primaryCounterList) {
				
				Token tokenAlreadyAssigned= counter.getToken();
				if(tokenAlreadyAssigned==null||(!tokenAlreadyAssigned.isTokenActive())) {
					counterFinal=counter;
					break;
				}
			}
		}else {
			for (Counter counter : seconadryCounterList) {
				
				Token tokenAlreadyAssigned= counter.getToken();
				if(tokenAlreadyAssigned==null||(!tokenAlreadyAssigned.isTokenActive())) {
					counterFinal=counter;
					break;
				}
			}
		}
		
		if(counterFinal!=null) {
			counterFinal.setToken(incomingToken);
			return counterRepository.save(counterFinal);
		}else {
			throw new CustomException("No Counter Available, All Counters are occupied, please try later, or mark active token complete!");
		}
		
	}
	
	
	//Below Method will mark a token assigned complete, and make counter available
	  @PutMapping(path="/customer/token/{tokenId}") 
	  public Token markTokenComplete(@PathVariable int tokenId) throws CustomException {
		
		  Token token=tokenRepository.findOne(tokenId);
	       if(token==null) { 
	    	   	throw new CustomException("Counter dies not exists"); 
	    	   }
 
	       token.setTokenActive(false);
	       return tokenRepository.save(token);
	     }

	
}
