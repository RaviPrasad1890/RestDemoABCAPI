package com.abc.bank.tokenservice.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;




@Controller("error")
public class ErrorConttroller {
	
	private static Logger logger=LoggerFactory.getLogger(ErrorConttroller.class);
	
	@ExceptionHandler(Exception.class)
	public String handleException
		(HttpServletRequest request, Exception ex){
		//ModelAndView mv = new ModelAndView();
		logger.info("Exception "+ex.getLocalizedMessage());
		logger.info("URL "+request.getRequestURL());
		return "Something went wrong, Please see error log for detail! ";
	}

}
