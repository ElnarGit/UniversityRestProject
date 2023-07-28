package com.saparov.UniversityProject.repository;


import com.saparov.UniversityProject.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findByFirstname(String firstname);

    List<Teacher> findBySubject(String subject);
}
