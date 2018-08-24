package com.ernok.stacktest01.repositories;

import com.ernok.stacktest01.models.Person;
import com.ernok.stacktest01.payload.ParticipantPayload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// Repository interface for accessing user data table

@Repository
public interface PeopleRepo extends JpaRepository<Person, Long> {
    List<ParticipantPayload> selectParticipants(@Param("firstName") String firstName);
}

