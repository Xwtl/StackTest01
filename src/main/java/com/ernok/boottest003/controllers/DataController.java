package com.ernok.boottest003.controllers;

import com.ernok.boottest003.models.Person;
import com.ernok.boottest003.models.Project;
import com.ernok.boottest003.models.Workplace;
import com.ernok.boottest003.payload.Participants;
import com.ernok.boottest003.repositories.PeopleRepo;
import com.ernok.boottest003.repositories.ProjectRepo;
import com.ernok.boottest003.repositories.WorkplaceRepo;
import com.ernok.boottest003.services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

// REST controller for accessing the database.
// Sends requests to service layer and returns fetched values.

@RestController
@RequestMapping("/data")
public class DataController {

    private final DataService dataService;
    private final PeopleRepo people;
    private final ProjectRepo projects;
    private final WorkplaceRepo wPlaces;

    // Constructors

    @Autowired
    public DataController(DataService dataService,
                          PeopleRepo people,
                          ProjectRepo projects,
                          WorkplaceRepo wPlaces) {
        this.dataService = dataService;
        this.people = people;
        this.projects = projects;
        this.wPlaces = wPlaces;
    }

    // Mappings for people requests

    @GetMapping("/people/{personID}")
    public Person getPerson(@PathVariable Long personID) {
        return dataService.getPerson(personID);
    }

    @GetMapping("/people")
    public Page<Person> getPeople(Pageable pageable) {
        return dataService.getPeople(pageable);
    }

    @PostMapping("/people")
    public ResponseEntity<?> createPerson(@Valid @RequestBody Person person) {
        return dataService.createPerson(person);
    }

    @DeleteMapping("/people/{personID}")
    public ResponseEntity<?> deletePerson(@PathVariable Long personID) {
        return dataService.deletePerson(personID);
    }

    // Mappings for project requests

    @GetMapping("/projects/{projectID}")
    public Project getProject(@PathVariable Long projectID) {
        return dataService.getProject(projectID);
    }

    @GetMapping("/projects")
    public Page<Project> getProjects(Pageable pageable) {
        return dataService.getProjects(pageable);
    }

    @PostMapping("/projects")
    public ResponseEntity<?> createProject(@Valid @RequestBody Project project) {
        return dataService.createProject(project);
    }

    @DeleteMapping("/projects/{projectID}")
    public ResponseEntity<?> deleteProject(@PathVariable Long projectID) {
        return dataService.deleteProject(projectID);
    }

    // Mappings for workplace requests

    @GetMapping("/workplaces/{workplaceID}")
    public Workplace getWorkplace(@PathVariable Long workplaceID) {
        return dataService.getWorkplace(workplaceID);
    }

    @GetMapping("/workplaces")
    public Page<Workplace> getWorkplaces(Pageable pageable) {
        return dataService.getWorkplaces(pageable);
    }

    @PostMapping("/workplaces")
    public ResponseEntity<?> createWorkplace(@Valid @RequestBody Workplace workplace) {
        return dataService.createWorkplace(workplace);
    }

    @DeleteMapping("/workplaces/{workplaceID}")
    public ResponseEntity<?> deleteWorkplace(@PathVariable Long workplaceID) {
        return dataService.deleteWorkplace(workplaceID);
    }

    ///////////////////////////////////////////////////////
    /////////////////////// TESTING ///////////////////////
    ///////////////////////////////////////////////////////

    @GetMapping("/test/projects/{firstName}")
    public List<Participants> getParticipants(@PathVariable String firstName) {
        return dataService.getParticipants(firstName);
    }

    @GetMapping("/test")
    public ResponseEntity<?> testQuery() {
        return dataService.testQuery();
    }

    @GetMapping("/test/{firstName}")
    public ResponseEntity<?> testQuery(@PathVariable String firstName) {
        return dataService.testQuery(firstName);
    }

}
