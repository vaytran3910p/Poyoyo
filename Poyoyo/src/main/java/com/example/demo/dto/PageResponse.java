package com.example.demo.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageResponse<T> {
	private List<T> content;
	private int currentPages;
	private int totalPages;
	private Long totalItems;
	private int itemPerPages;
}
