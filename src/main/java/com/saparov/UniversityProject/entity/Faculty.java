package com.saparov.UniversityProject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "faculty")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "faculty_name", unique = true)
    private String facultyName;

    @OneToMany(mappedBy = "faculty")
    private List<Student> students;
}
