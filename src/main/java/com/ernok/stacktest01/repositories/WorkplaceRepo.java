package com.ernok.stacktest01.repositories;

import com.ernok.stacktest01.models.Workplace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository interface for accessing workplace data table

@Repository
public interface WorkplaceRepo extends JpaRepository<Workplace, Long> {}
