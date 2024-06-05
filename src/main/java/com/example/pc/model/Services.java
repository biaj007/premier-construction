package com.example.pc.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import java.sql.Date;

@Entity
@Data
@Table(name = "services")
public class Services {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(name = "service_name")
	private String service_name;
	@Column(name = "content")
	private String content;
	@Column(name = "image")
	private String imagename;
	@Column(name = "icon")
	private String iconname;
	private Date createdon;
	private String image_path;
	@Transient
	private String simage;
	@Transient
	private String sicon;
}
