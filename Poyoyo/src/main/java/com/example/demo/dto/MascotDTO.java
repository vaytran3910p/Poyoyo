package com.example.demo.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MascotDTO {
	private Long MascotId;
	private String MascotCode;
	private String MascotName;
	private Long CharId;
	private String Status;
	private int isActive;
	private int isDelete;

}
