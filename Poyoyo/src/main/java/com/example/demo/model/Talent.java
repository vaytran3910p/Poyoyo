package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="talent")
public class Talent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long talentId;
	
	private String talentName;
	private String talentCode;
	private String category;
	private String status;
	private int isActive;
	private int isDelete;
	
}
