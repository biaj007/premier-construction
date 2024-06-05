package com.example.pc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "contact")
public class Contact {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;
	private String name;
	private String email;
	private String phone;
	private String office_address;
	private String embed_map;
	

}
