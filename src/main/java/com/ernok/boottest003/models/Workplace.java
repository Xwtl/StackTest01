package com.ernok.boottest003.models;

import javax.persistence.*;

// Data model representing workplaces & pSQL workplaces table

@Entity
@Table(name = "workplaces")
public class Workplace {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workplace_id;

    @Column(unique = true) private String workplaceName;

    // Constructors

    protected Workplace() {}

    public Workplace(String workplaceName) {
        this.workplaceName = workplaceName;
    }

    // Setters & getters

    public String getWorkplaceName() { return workplaceName; }
    public void setWorkplaceName(String workplaceName) {
        this.workplaceName = workplaceName;
    }
}

