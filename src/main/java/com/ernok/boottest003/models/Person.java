package com.ernok.boottest003.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

// Data model representing users & pSQL user table

@Entity
@Table(name = "people")
public class Person {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long person_id;

    @Column                private String firstName;
    @Column                private String lastName;
    @Column(unique = true) private String email;
    @Column                private int    userNum;

    // Create a many-to-one relationship with workplace table

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workplace_id")
    private Workplace workplace;

    // Create a many-to-many relationship with projects table

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
        cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        }
    )
    @JoinTable(name = "people_projects",
        joinColumns = { @JoinColumn(name = "person_id") },
        inverseJoinColumns = { @JoinColumn(name = "project_id") }
    )
    private Set<Project> projects = new HashSet<>();

    // Constructors

    protected Person() {}

    public Person(String firstName, String lastName,
                  String email, int userNum) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userNum = userNum;
    }

    // Setters & getters

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserNum() { return userNum; }
    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public Workplace getWorkplace() { return workplace; }
    public void setWorkplace(Workplace workplace) {
        this.workplace = workplace;
    }

    public Set<Project> getProjects() { return projects; }
    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
