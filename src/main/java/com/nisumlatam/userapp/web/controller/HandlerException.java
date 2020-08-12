package com.nisumlatam.userapp.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.nisumlatam.userapp.domain.dto.ErrorDto;
import com.nisumlatam.userapp.web.exceptions.BussinesValidationException;
import com.nisumlatam.userapp.web.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class HandlerException {

	@ExceptionHandler(value = { ObjectNotFoundException.class, BussinesValidationException.class,
			BadCredentialsException.class })
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		ErrorDto error = ErrorDto.builder().withMensaje(ex.getMessage()).build();
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}

}
