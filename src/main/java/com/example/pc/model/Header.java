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
@Table(name = "header_content")
public class Header{
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String stext;
	    private String btext;
	    @Column(name = "bgimage")
	    private String imagename;
	    private String stext1;
	    private String btext1;
	    @Column(name = "bgimage1")
	    private String imagename1;
	    private String stext2;
	    private String btext2;
	    private String imagepath;
	    @Column(name = "bgimage2")
	    private String imagename2;
	    @Transient
	    private String sbgimage;
	    @Transient
	    private String sbgimage1;
	    @Transient
	    private String sbgimage2;
		
	}


