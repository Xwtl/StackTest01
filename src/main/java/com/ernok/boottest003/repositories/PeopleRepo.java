package com.ernok.boottest003.repositories;

import com.ernok.boottest003.models.Person;
import com.ernok.boottest003.payload.Participants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;
import java.util.List;
import java.util.Optional;

// Repository interface for accessing user data table

@Repository
public interface PeopleRepo extends JpaRepository<Person, Long> {

    List<Participants> selectParticipants(@Param("firstName") String firstName);

    @Query(
            value = "SELECT project_name, first_name from projects INNER JOIN people_projects ON people_projects.project_id = projects.project_id INNER JOIN people ON people.person_id = people_projects.person_id",
            nativeQuery = true
    )
    List<List<?>> findTest();

    @Query(
            value = "SELECT first_name FROM people INNER JOIN workplaces ON people.workplace_id = workplaces.workplace_id WHERE workplace_name = :nimi",
            nativeQuery = true
    )
    List<?> findTest(@Param("nimi") String nimi);

    @Query(
            value = "SELECT last_name FROM people WHERE first_name = :firstName",
            nativeQuery = true
    )
    List<Person> findProjectParticipants(@Param("firstName") String firstName);
}

