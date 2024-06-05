package com.example.pc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pc.model.Header;


@Repository
public interface HeaderRepository extends JpaRepository<Header,Long> {
	List<Header> findAll();
}

