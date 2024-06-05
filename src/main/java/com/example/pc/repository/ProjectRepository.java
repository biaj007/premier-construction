package com.example.pc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.pc.model.Project;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
	List<Project> findByIsfeaturedOrderByDateDesc(int isfeatured, Pageable pageable);
    List<Project> findByIsfeatured(int isfeatured);
    List<Project> findAll();
    Optional<Project> findById(Long id);
    @Query(value = "select * from projects where title like %:inputquery% or city like %:inputquery% or location like %:inputquery%", nativeQuery = true)
    List<Project> filterProjects(@Param("inputquery") String inputquery);
}
