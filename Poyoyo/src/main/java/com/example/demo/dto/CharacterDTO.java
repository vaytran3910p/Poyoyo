package com.example.demo.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CharacterDTO {

	private Long CharId;
	private String CharName;
	private String CharCode;
	private String Category;
	private String Status;
	private int isActive;
	private int isDelete;
	

}
