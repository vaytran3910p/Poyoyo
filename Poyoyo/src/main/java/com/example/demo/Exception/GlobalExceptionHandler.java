package com.example.demo.Exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.dto.Error.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorResponse handleValidateError (MethodArgumentNotValidException ex, WebRequest request) {
		List<String> errors = ex.getBindingResult()
								.getFieldErrors()
								.stream()
								.map(error ->  error.getDefaultMessage())
								.collect(Collectors.toList());
		return new ErrorResponse(HttpStatus.BAD_REQUEST,errors, request.getDescription(false).replace("url=", ""));
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ErrorResponse handleResourceNotFoundError (ResourceNotFoundException ex, WebRequest request) {
		return new ErrorResponse(HttpStatus.NOT_FOUND, List.of(ex.getMessage()), request.getDescription(false));
	}
	
	@ExceptionHandler(Exception.class) 
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse handleAllExcetion(Exception ex, WebRequest request) {
		ex.printStackTrace();
		return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, List.of("Server error occurred"), request.getDescription(false));
	}
}
