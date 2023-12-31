package com.saparov.UniversityProject.service;


import com.saparov.UniversityProject.entity.Student;
import com.saparov.UniversityProject.exception.NotFoundException;
import com.saparov.UniversityProject.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentService {

    private final StudentRepository studentRepository;

    public Map<String, Object> getAllStudents(Pageable pageable){
        Map<String, Object> response = new HashMap<>();
        Page<Student> studentPage = studentRepository.findAll(pageable);

        response.put("products",   studentPage.getContent());
        response.put("currentPage", studentPage.getNumber());
        response.put("totalItems", studentPage.getTotalElements());
        response.put("totalPages", studentPage.getTotalPages());

        return response;
    }

    public Student getStudentById(Long id){
        return studentRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Student not found with id: " + id));
    }

    public List<Student> getStudentByCourse(Integer course){
       List<Student> students = studentRepository.findByCourse(course);
       return students;
    }

    public List<Student> getStudentByFirstname(String firstname){
        List<Student> students = studentRepository.findByFirstname(firstname);
        return students;
    }

    @Transactional
    public Student createStudent(Student student){
        return studentRepository.save(student);
    }

    @Transactional
    public Student updateStudent(Long id, Student student){
        Student updateStudent = getStudentById(id);

        updateStudent.setFirstname(student.getFirstname());
        updateStudent.setLastname(student.getLastname());
        updateStudent.setEmail(student.getEmail());
        updateStudent.setPhoneNumber(student.getPhoneNumber());
        updateStudent.setCourse(student.getCourse());
        updateStudent.setFaculty(student.getFaculty());

        return studentRepository.save(updateStudent);
    }

    @Transactional
    public void deleteStudent(Long id){
        Student student = getStudentById(id);
        studentRepository.delete(student);
    }
}
