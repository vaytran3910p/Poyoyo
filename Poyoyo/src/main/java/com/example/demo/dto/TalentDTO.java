package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TalentDTO {

	private Long TalentId;
	private String TalentName;
	private String TalentCode;
	private String Category;
	private String Status;
	private int isActive;
	private int isDelete;
	

}
