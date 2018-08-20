package com.ernok.boottest003.repositories;

import com.ernok.boottest003.models.Person;
import com.ernok.boottest003.payload.ParticipantPayload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// Repository interface for accessing user data table

@Repository
public interface PeopleRepo extends JpaRepository<Person, Long> {
    List<ParticipantPayload> selectParticipants(@Param("firstName") String firstName);
}

