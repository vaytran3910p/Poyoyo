package com.example.demo.repository.CustomRepo;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.dto.UserDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {
	
	@PersistenceContext
    EntityManager manager ;
	
	@Override
	public List<UserDTO> getLstUserWithCompleteInfo() {
		StringBuilder sql = new StringBuilder();
		sql
		.append(" select user_id userid, user_name, pass_word, email, status ")
		.append(" from user ")
		.append(" where user_name is not null ")
		.append(" and pass_word is not null ")
		.append(" and status =  :status ");
		List<Object[]> data = manager.createNativeQuery(sql.toString())
									 .setParameter("status","ACT")
									 .getResultList();
		return data.stream().map(
				row -> new UserDTO(
					(Long) row[0],   // userId
 					(String) row[1], // username
					(String) row[2], // password
					(String) row[3], // email
					(String) row[4]  // status
				)).toList();
	}
}	
