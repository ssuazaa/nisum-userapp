package com.nisumlatam.userapp.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BussinesValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2509743736170318978L;

	public BussinesValidationException(String message) {
		super(message);
	}

	public BussinesValidationException(Errors errors) {
		super(errors.getAllErrors().get(0).getDefaultMessage());
	}

}
