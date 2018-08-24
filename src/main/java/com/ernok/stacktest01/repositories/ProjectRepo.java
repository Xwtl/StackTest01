package com.ernok.stacktest01.repositories;

import com.ernok.stacktest01.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository interface for accessing project data table

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long> {}
