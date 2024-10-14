package com.jpa_hibernate.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "student")
@NamedQueries(
        @NamedQuery(name = "getAll", query = "SELECT s FROM StudentQueries s"
        )
)
public class StudentQueries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    @Override
    public String toString() {
        return "StudentQueries{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
