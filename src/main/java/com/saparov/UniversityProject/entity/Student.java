package com.saparov.UniversityProject.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Course must not be empty")
    @Min(value = 1, message = "Course must be at least 1")
    @Max(value = 4, message = "Course cannot be greater than 4")
    private Integer course;


    @NotNull(message = "Faculty must not be empty")
    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;
}
