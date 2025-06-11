package com.example.demo.dto;

import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
	private Long userId;
	private String userName;
	private String passWord;
	private String status; 
	private Set<String> roles;
	private int isDelete;
	
	public UserDTO (Long userId,String userName, String passWord, String status) {
		this.userId = userId;
		this.userName = userName;
		this.passWord = passWord;
		this.status = status;
	}
}

