package com.example.demo.repository.CustomRepo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.TalentDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class TalentRepositoryImpl implements TalentRepositoryCustom {
	@PersistenceContext
	EntityManager manager;
	
	@Override
	public List<TalentDTO> findTalentIsValid() {
		StringBuilder sql = new StringBuilder();
		sql
			.append(" select talent_id talentId, talent_name talentName, talent_code talentCode,  category, status ")
			.append(" from talent ")
			.append(" where talent_code is not null ") 
			.append(" and status = :status ");
		List<Object[]> data = manager.createNativeQuery(sql.toString())
							.setParameter("status", "ACT")  
							.getResultList();
		return data.stream().map(row -> new TalentDTO(
				(Long) row[0],   // Id
				(String) row[1], // name
				(String) row[2], // code
				(String) row[3], // category
				(String) row[4]  // status
		)).toList();
	}
	@Override
	public Page<TalentDTO> findTalentIsValidWithPagination(Pageable page) {
		StringBuilder sql = new StringBuilder();
		sql
		.append(" select talent_id talentId, talent_name talentName, talent_code talentCode,  category, status ")
		.append(" from talent ")
		.append(" where talent_code is not null ") 
		.append(" and status = :status ");
		StringBuilder countSql = new StringBuilder();
		countSql.append(" select count(*) ")
				.append(" from ( ")
				.append(sql.toString())
				.append(" ) as tmp ");
				
		List<Object[]> data = manager.createNativeQuery(sql.toString())
									.setParameter("status", "ACT") 
									.setFirstResult((int)page.getOffset())
									.setMaxResults(page.getPageSize())
									.getResultList();
		List<TalentDTO> content = data.stream().map(row -> new TalentDTO(
				(Long) row[0],   // Id
				(String) row[1], // name
				(String) row[2], // code
				(String) row[3], // category
				(String) row[4]  // status
				)).toList();
		Long total = ((Number) manager.createNativeQuery(countSql.toString())
									.setParameter("status", "ACT")			
									.getSingleResult()).longValue();
		return new PageImpl<TalentDTO>(content,page,total);
	}
}
