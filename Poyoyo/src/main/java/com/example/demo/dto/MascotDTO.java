package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MascotDTO {
	private Long mascotId;
	private String mascotCode;
	private String mascotName;
	private Long charId;
	private String status;
	private int isActive;
	private int isDelete;
	
	public MascotDTO (Long mascotId, String mascotCode, String mascotName, Long charId, String status) {
		this.mascotId = mascotId;
		this.mascotCode = mascotCode;
		this.mascotName = mascotName;
		this.charId = charId;
		this.status = status;
	}

}
