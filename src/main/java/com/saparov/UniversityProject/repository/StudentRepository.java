package com.saparov.UniversityProject.repository;


import com.saparov.UniversityProject.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByFirstname(String firstname);

    List<Student> findByCourse(Integer course);
}
