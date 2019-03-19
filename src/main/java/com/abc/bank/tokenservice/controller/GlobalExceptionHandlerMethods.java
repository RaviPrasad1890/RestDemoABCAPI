package com.abc.bank.tokenservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandlerMethods {
	private static Logger logger=LoggerFactory.getLogger(GlobalExceptionHandlerMethods.class);
	
	
	
	@ExceptionHandler(value=CustomException.class)
	public String handlingCustomException(Exception e) {
		//Logging Null Pointer Exception 
		System.out.println("Null Pointer Exception");
		logger.info("Exception "+e.getLocalizedMessage());
		return e.getLocalizedMessage();
	}
	
	
	@ExceptionHandler(value=NullPointerException.class)
	public String handlingNullPointerException(Exception e) {
		//Logging Null Pointer Exception 
		logger.info("Exception "+e.getLocalizedMessage());
		return "Data Not Found";
	}

}
