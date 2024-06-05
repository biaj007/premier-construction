package com.example.pc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pc.model.Admin;


@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
	Admin findByAuserAndApass(String auser, String apass);
}

