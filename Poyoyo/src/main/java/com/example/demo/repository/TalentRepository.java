package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Talent;
import com.example.demo.repository.CustomRepo.TalentRepositoryCustom;

public interface TalentRepository extends JpaRepository<Talent, Long>,TalentRepositoryCustom {

}
