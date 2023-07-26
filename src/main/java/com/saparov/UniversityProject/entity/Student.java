package com.saparov.UniversityProject.entity;


import com.saparov.UniversityProject.enums.Faculty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Student extends Person{

    @Column(name = "course")
    private Integer course;

    @Column(name = "faculty")
    @Enumerated(EnumType.STRING)
    private Faculty faculty;
}
