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
@Table(name="mascot")
public class Mascot {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long MascotId;
	
	private String MascotCode;
	private String MascotName;
	private Long CharId;
	private String Status;
	private int isActive;
	private int isDelete;
}
