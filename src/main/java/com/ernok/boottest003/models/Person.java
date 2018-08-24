package com.ernok.boottest003.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

// Data model representing users & pSQL user table

// SQL query method, used for mapping SQL query responses to Java objects
@SqlResultSetMapping(
        name = "ParticipantsMapping",
        columns = {
                @ColumnResult(name = "projectName", type = String.class),
                @ColumnResult(name = "startDate",   type = LocalDate.class),
                @ColumnResult(name = "firstName",   type = String.class) }
)
@NamedNativeQuery(
        name = "Person.selectParticipants",
        query = "SELECT project_name as projectName, start_date as startDate, first_name as firstName" +
                " from projects INNER JOIN people_projects ON people_projects.project_id = projects.project_id" +
                " INNER JOIN people ON people.person_id = people_projects.person_id WHERE first_name = :firstName",
        resultSetMapping = "ParticipantsMapping"
)
@Entity
@Table(name = "people")
public class Person {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long person_id;

    @Column                private String firstName;
    @Column                private String lastName;
    @Column(unique = true) private String email;
    @Column(unique = true) private int    userNum;

    // Creates a many-to-one relationship with workplaces table.
    // Creates a workplace_id foreign key in the people-table.

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workplace_id")
    private Workplace workplace;

    // Creates a many-to-many relationship with projects table.
    // Defines a junction table (people_projects) with
    // foreign key identifiers from projects-table and people-table.

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
        cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE }
    )
    @JoinTable(name = "people_projects",
        joinColumns =        { @JoinColumn(name = "person_id") },
        inverseJoinColumns = { @JoinColumn(name = "project_id") }
    )
    private Set<Project> projects = new HashSet<>();

    // Constructors

    protected Person() {}

    public Person(String firstName, 
                  String lastName,
                  String email, 
                  int userNum) {
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
