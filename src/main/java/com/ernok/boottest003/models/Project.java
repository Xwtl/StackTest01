package com.ernok.boottest003.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

// Data model representing projects & pSQL project table

@Entity
@Table(name = "projects")
public class Project {

    // Attributes

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long project_id;

    @Column(unique = true) private String projectName;
    @Column private LocalDate startDate;
    @Column private LocalDate endDate;

    // Create a many-to-many relationship with people table

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "projects"
    )
    private Set<Person> people = new HashSet<>();

    // Constructors

    protected Project() {}

    public Project(String projectName, LocalDate startDate, LocalDate endDate) {
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Setters & getters

    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Set<Person> getPeople() { return people; }
    public void setPeople(Set<Person> people) {
        this.people = people;
    }
}
