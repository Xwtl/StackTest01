package com.ernok.stacktest01.payload;

import java.time.LocalDate;

// Payload interface for mapping People-table SQL query results into.
// Helps with accessing specific data in the query results.

public interface ParticipantPayload {
    String getProjectName();
    LocalDate getStartDate();
    String getFirstName();
}