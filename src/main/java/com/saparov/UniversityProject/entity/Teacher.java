package com.saparov.UniversityProject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "teacher")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Teacher extends Person{

    @Column(name = "subject")
    @NotBlank(message = "Subject must not be empty")
    private String subject;

    @Column(name = "salary")
    @NotNull(message = "Salary must not be empty")
    @Min(value = 1000, message = "Salary must be at least 1000")
    @Max(value = 6000, message = "Salary cannot be greater than 6000")
    private Double salary;
}
