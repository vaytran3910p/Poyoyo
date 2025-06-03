package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TodoDTO {
	private Long id;
	private String title;
	private String description;
	private String status;
	private String priority;
	private LocalDateTime dueDate;
	private LocalDateTime completedAt;
	private LocalDateTime createAt;
	private LocalDateTime updateAt;
	private String userName;
	private String categoryName;
}
