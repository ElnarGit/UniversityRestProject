package com.saparov.UniversityProject.repository;


import com.saparov.UniversityProject.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByFirstname(String firstname);

    List<Teacher> findBySubject(String subject);
}
