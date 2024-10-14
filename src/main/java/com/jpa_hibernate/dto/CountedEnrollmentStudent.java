package com.jpa_hibernate.dto;

import com.jpa_hibernate.entities.StudentQueries;

public record CountedEnrollmentStudent(
        StudentQueries s,
        Long count
) {
}
