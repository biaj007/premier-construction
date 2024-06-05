package com.example.pc.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pc.model.Message;


@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
	List<Message> findAll();
}

