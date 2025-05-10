package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
	private Long userId;
	private String userName;
	private String passWord;
	private String email;
	private String status; 
	private int isActive;
	private int isDelete;
	
	public UserDTO (Long userId,String userName, String passWord, String email, String status) {
		this.userId = userId;
		this.userName = userName;
		this.passWord = passWord;
		this.email = email;
		this.status = status;
	}
}

