package com.ernok.boottest003.services;

import com.ernok.boottest003.exceptions.ResourceNotFoundException;
import com.ernok.boottest003.models.Person;
import com.ernok.boottest003.models.Project;
import com.ernok.boottest003.models.Workplace;
import com.ernok.boottest003.payload.Participants;
import com.ernok.boottest003.repositories.PeopleRepo;
import com.ernok.boottest003.repositories.ProjectRepo;
import com.ernok.boottest003.repositories.WorkplaceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;
import java.util.List;

// Service containing the database access methods

@Service
public class DataService {

    private final PeopleRepo peopleRepo;
    private final ProjectRepo projectRepo;
    private final WorkplaceRepo workplaceRepo;

    // Constructors

    @Autowired
    public DataService(PeopleRepo peopleRepo,
                       ProjectRepo projectRepo,
                       WorkplaceRepo workplaceRepo) {
        this.peopleRepo = peopleRepo;
        this.projectRepo = projectRepo;
        this. workplaceRepo = workplaceRepo;
    }

    // People database methods

    public Person getPerson(Long personID) {
        return peopleRepo.findById(personID).orElseThrow( () ->
                new ResourceNotFoundException("Person not found."));
    }

    public Page<Person> getPeople(Pageable pageable) {
        return peopleRepo.findAll(pageable);
    }

    public ResponseEntity<?> createPerson(Person person) {
        peopleRepo.save(person);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> deletePerson(Long personID) {
        return peopleRepo.findById(personID)
                .map(person -> {
                    peopleRepo.delete(person);
                    return ResponseEntity.ok().build();
                }).orElseThrow( () ->
                        new ResourceNotFoundException("Person not found."));
    }

    // Project database methods

    public Project getProject(Long projectID) {
        return projectRepo.findById(projectID).orElseThrow( () ->
                new ResourceNotFoundException("Project not found."));
    }

    public Page<Project> getProjects(Pageable pageable) {
        return projectRepo.findAll(pageable);
    }

    public ResponseEntity<?> createProject(Project project) {
        projectRepo.save(project);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> deleteProject(Long projectID) {
        return projectRepo.findById(projectID)
                .map(project -> {
                    projectRepo.delete(project);
                    return ResponseEntity.ok().build();
                }).orElseThrow( () ->
                        new ResourceNotFoundException("Project not found."));
    }

    // Workplace database methods

    public Workplace getWorkplace(Long workplaceID) {
        return workplaceRepo.findById(workplaceID).orElseThrow( () ->
                new ResourceNotFoundException("Workplace not found"));
    }

    public Page<Workplace> getWorkplaces(Pageable pageable) {
        return workplaceRepo.findAll(pageable);
    }

    public ResponseEntity<?> createWorkplace(Workplace workplace) {
        workplaceRepo.save(workplace);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> deleteWorkplace(Long workplaceID) {
        return workplaceRepo.findById(workplaceID)
                .map(workplace -> {
                    workplaceRepo.delete(workplace);
                    return ResponseEntity.ok().build();
                }).orElseThrow( () ->
                        new ResourceNotFoundException("Workplace not found."));
    }

    ///////////////////////////////////////////////////////
    /////////////////////// TESTING ///////////////////////
    ///////////////////////////////////////////////////////

    /*
    public ResponseEntity<?> testQuery() {
        peopleRepo.findTest().ifPresent(result ->
                System.out.println("Result type: " + ((Object) result).getClass()));
        return ResponseEntity.ok().build();
    }
    */

    public List<Participants> getParticipants(String firstName) {
        List<Participants> participants = peopleRepo.selectParticipants(firstName);
        System.out.println("Participants: ");
        participants.forEach(item -> System.out.println("   - " + item.getFirstName()
                                                 + "\n      " + item.getProjectName()
                                                 + "\n      " + item.getStartDate()));
        return participants;
    }

    public ResponseEntity<?> testQuery() {
        List<List<?>> result = peopleRepo.findTest();
        System.out.println("Result type: " + result.getClass());
        result.forEach(item -> System.out.println("   Item: " + item.toString()));
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> testQuery(String firstName) {
        List<?> result = peopleRepo.findTest(firstName);
        System.out.println("Result type: " + result.getClass());
        result.forEach(item -> System.out.println("   Item: " + item.toString()));
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> findProjectParticipants(String projectName) {
        List<Person> result = peopleRepo.findProjectParticipants(projectName);
        System.out.println("Result type: " + result.getClass());
        result.forEach(person -> System.out.println("   Object name: " +
                                            person.getFirstName() + " " +
                                            person.getLastName()));
        return ResponseEntity.ok().build();
    }
}
