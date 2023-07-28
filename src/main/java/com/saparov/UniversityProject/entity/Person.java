package com.saparov.UniversityProject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstname")
    @NotBlank(message = "Firstname must not be empty")
    private String firstname;

    @Column(name = "lastname")
    @NotBlank(message = "Lastname must not be empty")
    private String lastname;

    @Column(name = "email", unique = true)
    @NotBlank(message = "Email must not be empty")
    @Email(message = "Invalid email format")
    private String email;

    @Column(name = "phone_number", unique = true)
    @NotBlank(message = "Phone number must not be empty")
    private String phoneNumber;
}
