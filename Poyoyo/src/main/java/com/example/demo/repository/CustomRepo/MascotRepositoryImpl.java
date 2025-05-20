package com.example.demo.repository.CustomRepo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.example.demo.dto.MascotDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class MascotRepositoryImpl implements MascotRepositoryCustom {
	
	@PersistenceContext
	EntityManager manager;
	
	@Override
	public List<MascotDTO> findAllValidMascot() {
		
		StringBuilder sql = new StringBuilder();
		sql
			.append(" select mascot_id as mascotId, mascot_name as mascotName, mascot_code as mascotCode, char_id as charId , status ")
			.append(" from mascot ")
			.append(" where mascot_code is not null ")
			.append(" and mascot_name is not null ")
			.append(" and status = :status ")
		;
		List<Object[]> data = manager.createNativeQuery(sql.toString())
									 .setParameter("status", "ACT")
									 .getResultList();
		return data.stream().map(row -> new MascotDTO(
				(Long) row[0],
				(String) row[1],
				(String) row[2],
				(Long) row[3],
				(String) row[4]
		)).toList() ;
	}
	
	@Override
	public Page<MascotDTO> findAllValidMascotWithPage (Pageable page) {
		StringBuilder sql = new StringBuilder();
		StringBuilder countSql =  new StringBuilder();
		sql
			.append(" select mascot_id as mascotId, mascot_name as mascotName, mascot_code as mascotCode, char_id as charId , status ")
			.append(" from mascot ")
			.append(" where mascot_code is not null ")
			.append(" and mascot_name is not null ")
			.append(" and status = :status ")
		;
		countSql
			.append("select count(*) from ( ")
			.append(sql.toString())
			.append(" ) tmp ");
		List<Object[]> data = manager.createNativeQuery(sql.toString())
									 .setParameter("status", "ACT")
									 .getResultList();
		Long countData = ((Number) manager.createNativeQuery(countSql.toString())
								.setParameter("status", "ACT")
								.setMaxResults(page.getPageSize())
								.setFirstResult(page.getPageNumber())
								.getSingleResult()).longValue();
		List<MascotDTO> result = data.stream().map(row -> new MascotDTO(
				(Long) row[0],
				(String) row[1],
				(String) row[2],
				(Long) row[3],
				(String) row[4]
				)).toList();
		return new PageImpl<MascotDTO>(result, page, countData);
	}
}
