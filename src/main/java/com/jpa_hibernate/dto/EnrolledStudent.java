package com.jpa_hibernate.dto;

import com.jpa_hibernate.entities.Enrollment;
import com.jpa_hibernate.entities.StudentQueries;

public record EnrolledStudent (
        StudentQueries student,
        Enrollment enrollment
){

}
