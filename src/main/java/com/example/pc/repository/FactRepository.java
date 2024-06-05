package com.example.pc.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.pc.model.About;


@Repository
public interface FactRepository extends JpaRepository<About, Long> {
    @Query(value = "select (select count(*) from projects where isfeatured='1') as proj_cnt, " +
                   "(select count(distinct(lower(city))) from projects where isfeatured='1') as city_cnt, " +
                   "(SELECT DATEDIFF(NOW(), incorporatedon) AS day_difference FROM contact LIMIT 1) as day_cnt, " +
                   "50 as staff_cnt", nativeQuery = true)
    Map<String, Object> factAreaDtls();
}

