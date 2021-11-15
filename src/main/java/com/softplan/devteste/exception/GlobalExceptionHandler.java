package com.softplan.devteste.exception;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ErrorMessage> handleBusinessException(NegocioException exception, WebRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getError());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorMessage> handleBusinessException(ConstraintViolationException exception,
			WebRequest request) {

		List<ErrorField> errors = exception.getConstraintViolations().stream().map(cv -> {
			return new ErrorField(cv.getPropertyPath().toString(), cv.getMessage(),
					String.valueOf(cv.getInvalidValue()));
		}).collect(Collectors.toList());

		var errorMessage = new ErrorMessage(null, exception.getMessage(), errors);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> handleBusinessException(MethodArgumentNotValidException exception,
			WebRequest request) {

		List<ErrorField> errors = exception.getFieldErrors().stream().map(cv -> {
			return new ErrorField(cv.getField(), cv.getDefaultMessage(), String.valueOf(cv.getRejectedValue()));
		}).collect(Collectors.toList());

		var errorMessage = new ErrorMessage(ErrorMessageEnum.E004, errors);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
	}

}
