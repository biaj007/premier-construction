package com.example.pc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pc.model.Home;

import java.util.List;

@Repository
public interface HomeRepository extends JpaRepository<Home.Banner,Long> {
    List<Home.Banner> findAll();
}
