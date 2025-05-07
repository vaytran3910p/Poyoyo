package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Mascot;

public interface MascotRepository extends JpaRepository<Mascot, Long> {

}
