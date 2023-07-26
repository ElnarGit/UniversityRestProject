package com.saparov.UniversityProject.repository;


import com.saparov.UniversityProject.entity.Student;
import com.saparov.UniversityProject.enums.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByFirstname(String firstname);

    List<Student> findByFaculty(Faculty faculty);
}
