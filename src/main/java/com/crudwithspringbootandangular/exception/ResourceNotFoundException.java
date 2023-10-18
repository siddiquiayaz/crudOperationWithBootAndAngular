package com.crudwithspringbootandangular.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	private final static long serialVesionUID =1L;
	  public ResourceNotFoundException(String message) {
		  
		  super(message);
	  }

}
