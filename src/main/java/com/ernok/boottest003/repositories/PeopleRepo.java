package com.ernok.boottest003.repositories;

import com.ernok.boottest003.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository interface for accessing user data table

@Repository
public interface PeopleRepo extends JpaRepository<Person, Long> {}
