package com.architrack.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@ControllerAdvice
public class CustomResponseExceptionHandler {


	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseException>genericHandlerException(Exception ex, WebRequest http){
		
		ResponseException response = new ResponseException(http.getDescription(false), ex.getMessage(), new Date());
		return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(ResponseNotFoundHandlerException.class)
	public ResponseEntity<ResponseException>notFoundHandlerException(Exception ex, WebRequest http){
		
		ResponseException response = new ResponseException(http.getDescription(false), ex.getMessage(), new Date());
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(ResponseBadRequestHandlerException.class)
	public ResponseEntity<ResponseException>badRequestHandlerException(Exception ex, WebRequest http){
		
		ResponseException response = new ResponseException(http.getDescription(false), ex.getMessage(), new Date());
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(InvalidJwtAuthenticationException.class)
	public ResponseEntity<ResponseException>InvalidAuthenticationException(Exception ex, WebRequest http){
		
		ResponseException response = new ResponseException(ex.getMessage(), http.getDescription(false), new Date());
		return new ResponseEntity<>(response,HttpStatus.FORBIDDEN);
	}
	
}
