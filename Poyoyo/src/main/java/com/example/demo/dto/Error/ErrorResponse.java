package com.example.demo.dto.Error;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ErrorResponse {
	private String timestamp;
	private int status;
	private List<String> errors;
	private String path;
	
	public ErrorResponse (HttpStatus status, List<String> errors, String path) {
		this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
		this.status = status.value();
		this.errors = errors;
		this.path = path;
	}
}
