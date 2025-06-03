package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDTO {
	private Long id;
	private String name;
	private String description;
	private String colorCode;
	private LocalDateTime createAt;
	private String userName;
	private List<Long> todoIds;
}
