package com.ernok.boottest003.payload;

import java.time.LocalDate;

public interface Participants {
    String getProjectName();
    LocalDate getStartDate();
    String getFirstName();
}

/*
public class Participants {

    private String projectName;
    private LocalDate startDate;
    private String firstName;

    public Participants(String projectName, LocalDate startDate, String firstName) {
        this.projectName = projectName;
        this.startDate = startDate;
        this.firstName = firstName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
*/