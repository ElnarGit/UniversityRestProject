package com.saparov.UniversityProject.service;


import com.saparov.UniversityProject.entity.Student;
import com.saparov.UniversityProject.exception.NotFoundException;
import com.saparov.UniversityProject.repository.StudentRepository;
import jakarta.validation.Valid;
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

    public List<Student> findByCourse(Integer course){
       List<Student> students = studentRepository.findByCourse(course);

       if(students.isEmpty()){
           throw new NotFoundException("Student not found with course: " + course);
       }
       return students;
    }


    public Student getStudentById(Long id){
        return studentRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Student not found with id: " + id));
    }

    public List<Student> getStudentByFirstname(String firstname){
        List<Student> students = studentRepository.findByFirstname(firstname);

        if(students.isEmpty()){
            throw new NotFoundException("Student not found with firstname: " + firstname);
        }
        return students;
    }



    @Transactional
    public Student createStudent(@Valid Student student){
        return studentRepository.save(student);
    }

    @Transactional
    public Student updateStudent(Long id, @Valid Student student){
        Student updateStudent = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student not found with id: " + id));

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
        studentRepository.findById(id)
                        .orElseThrow(() ->  new NotFoundException("Student not found with id: " + id));

        studentRepository.deleteById(id);
    }
}
