package com.example.pc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pc.model.About;


@Repository
public interface AboutRepository extends JpaRepository<About,Long> {
	List<About> findAll();
}

