package com.architrack.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResponseNotFoundHandlerException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	
	public ResponseNotFoundHandlerException(String msg) {
		super(msg);
	}

}
