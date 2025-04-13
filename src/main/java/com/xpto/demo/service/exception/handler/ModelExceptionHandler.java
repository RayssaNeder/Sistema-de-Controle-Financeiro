package com.xpto.demo.service.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.xpto.demo.service.error.Error;
import com.xpto.demo.service.exception.ModelException;

@RestControllerAdvice
public class ModelExceptionHandler {

	@ExceptionHandler(ModelException.class)
	public ResponseEntity<Error> handleModelException(ModelException exception) {
		Error error = exception.getModel();
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

		if (error.getCode() != null) {
			if (error.getCode() == 404) {
				status = HttpStatus.NOT_FOUND;
			} else if (error.getCode() == 500) {
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}

		return new ResponseEntity<>(error, status);
	}
}
