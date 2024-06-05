package com.example.pc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pc.model.Services;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<Services,Long> {
    List<Services> findAll();
    Optional<Services> findById(Long id);
}
