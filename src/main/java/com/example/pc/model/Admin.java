package com.example.pc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "admin")
public class Admin{
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long aid;
	    private String auser;
	    private String apass;
 
}

