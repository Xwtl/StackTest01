package com.ernok.boottest003.repositories;

import com.ernok.boottest003.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository interface for accessing project data table

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long> {}
