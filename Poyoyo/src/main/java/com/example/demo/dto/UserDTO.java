package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
	private Long UserId;
	private String UserName;
	private String PassWord;
	private String status; 
	private int isActive;
	private int isDelete;
}

