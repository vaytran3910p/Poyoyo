package com.example.demo.dto;

import com.example.demo.model.Talent;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TalentDTO {

	private Long talentId;
	private String talentName;
	private String talentCode;
	private String category;
	private String status;
	private int isActive;
	private int isDelete;
	
	public TalentDTO (Long talentId, String talentName, String talentCode, String category, String status) {
		this.talentId =  talentId;
		this.talentName = talentName;
		this.talentCode = talentCode;
		this.category = category;
		this.status = status;
	}

}
