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
import java.util.StringJoiner;

@Entity
@Data
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;
    private String title;
    private String pcontent;
    private String type;
    private Date date;
    private String bhk;
    private int bedroom;
    private int bathroom;
    private int balcony;
    private int kitchen;
    private int hall;
    private String floor;
    private int size;
    private int price;
    private String location;
    private String city;
    private String state;
    @Column(name = "pimage")
    private String pimagename;
    @Column(name = "pimage1")
    private String pimage1name;
    @Column(name = "pimage2")
    private String pimage2name;
    @Column(name = "pimage3")
    private String pimage3name;
    @Column(name = "pimage4")
    private String pimage4name;
    @Column(name = "pimage5")
    private String pimage5name;
    @Column(name = "pimage6")
    private String pimage6name;
    private String pimage_all;
    private String status;
    private String totalfloor;
    private int isfeatured;
    private String client_name;
    private String complete_years;
    private String constr_duration;
    @Transient
    private String simage ;
    @Transient
    private String simage1 ;
    @Transient
    private String simage2 ;
    @Transient
    private String simage3 ;
    @Transient
    private String simage4 ;
    @Transient
    private String simage5 ;
    @Transient
    private String simage6 ;
    @Column(name = "image_path")
    private String imagePath;
    
    public String getImageAll() {
        StringJoiner joiner = new StringJoiner(",");
        addIfNotNull(joiner, pimagename);
        addIfNotNull(joiner, pimage1name);
        addIfNotNull(joiner, pimage2name);
        addIfNotNull(joiner, pimage3name);
        addIfNotNull(joiner, pimage4name);
        addIfNotNull(joiner, pimage5name);
        addIfNotNull(joiner, pimage6name);
        return joiner.toString();
    }

    private void addIfNotNull(StringJoiner joiner, String value) {
        if (value != null) {
            joiner.add(value);
        }
    }
    
     
}

