package com.example.pc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.sql.Date;


public class Home {
	
	@Entity
	@Data
	@Table(name = "header_content")
	public static class Banner{
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String stext;
	    private String btext;
	    private String bgimage;
	    private Date createdon;
	    private String stext1;
	    private String btext1;
	    private String bgimage1;
	    private String stext2;
	    private String btext2;
	    private String bgimage2;
		
		}
 
}

