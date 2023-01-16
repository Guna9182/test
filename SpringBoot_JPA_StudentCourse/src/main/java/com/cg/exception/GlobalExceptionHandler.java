package com.cg.exception;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ResponseBody
	@ExceptionHandler(value= {Exception.class})
	public ErrorInformation handleConflict(Exception ex, HttpServletRequest req) {
		
		
		String msg = ex.getMessage();
		String url = req.getRequestURI().toString();
		LocalDateTime dt = LocalDateTime.now();
		ErrorInformation response = new ErrorInformation(url, msg, dt);
		return response;
	}

}
