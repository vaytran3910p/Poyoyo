package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "category")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	

	private String description;
	
	@Column(name ="color_code")
	private String colorCode;
	
	@Column(name = "create_at")
	private LocalDateTime createAt;
	
	/*
	 * Note: áp dụng cho mỗi user đăng nhập vào app
	 * mỗi user khi đăng nhập vào app sẽ được phép tạo cho bản thân nhiều category
	 * gom lại thành 1 bộ user cho riêng mỗi người dùng
	 * */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Todo> todos;
}
