package com.example.pc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
@Table(name = "about")
public class About{
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String heading;
	    private String heading1;
	    @Column(name = "content")
	    private String content;
	    @Column(name = "content1")
	    private String content1;
	    @Column(name = "image")
	    private String imagename;
	    private String name;
	    private String role;
	    @Transient
	    private String simage;
 
}

