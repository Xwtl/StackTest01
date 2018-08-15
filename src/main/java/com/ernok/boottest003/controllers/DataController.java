package com.ernok.boottest003.controllers;

import com.ernok.boottest003.models.Person;
import com.ernok.boottest003.models.Project;
import com.ernok.boottest003.models.Workplace;
import com.ernok.boottest003.repositories.PersonRepo;
import com.ernok.boottest003.repositories.ProjectRepo;
import com.ernok.boottest003.repositories.WorkplaceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired private PersonRepo people;
    @Autowired private ProjectRepo projects;
    @Autowired private WorkplaceRepo wPlaces;

    @GetMapping("/people")
    public Page<Person> getPeople(Pageable pageable) {
        return people.findAll(pageable);
    }

    @PostMapping("/people")
    public Person createPerson(@Valid @RequestBody Person person) {
        return people.save(person);
    }

    @DeleteMapping("/people/{personID}")
    public ResponseEntity<?> deletePerson(@PathVariable Long personID) {
        return people.findById(personID)
                .map(person -> {
                    people.delete(person);
                    return ResponseEntity.ok().build();
                }).orElseThrow( () ->
                        new ResourceNotFoundException("Person not found."));
    }

    @GetMapping("/projects")
    public Page<Project> getProjects(Pageable pageable) {
        return projects.findAll(pageable);
    }

    @PostMapping("/projects")
    public Project createProject(@Valid @RequestBody Project project) {
        return projects.save(project);
    }

    @DeleteMapping("/projects/{projectID}")
    public ResponseEntity<?> deleteProject(@PathVariable Long projectID) {
        return projects.findById(projectID)
                .map(project -> {
                    projects.delete(project);
                    return ResponseEntity.ok().build();
                }).orElseThrow( () ->
                        new ResourceNotFoundException("Project not found."));
    }

    @GetMapping("/workplaces")
    public Page<Workplace> getWPlaces(Pageable pageable) {
        return wPlaces.findAll(pageable);
    }

    @PostMapping("/workplaces")
    public Workplace createWPlace(@Valid @RequestBody Workplace wPlace) {
        return wPlaces.save(wPlace);
    }

    @DeleteMapping("/workplaces/{wPlaceID}")
    public ResponseEntity<?> deleteWPlace(@PathVariable Long wPlaceID) {
        return wPlaces.findById(wPlaceID)
                .map(wPlace -> {
                    wPlaces.delete(wPlace);
                    return ResponseEntity.ok().build();
                }).orElseThrow( () ->
                        new ResourceNotFoundException("Project not found."));
    }
}
