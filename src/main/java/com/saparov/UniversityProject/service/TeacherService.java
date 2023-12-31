package com.saparov.UniversityProject.service;


import com.saparov.UniversityProject.entity.Teacher;
import com.saparov.UniversityProject.exception.NotFoundException;
import com.saparov.UniversityProject.repository.TeacherRepository;
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
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public Map<String, Object> getAllTeachers(Pageable pageable){

        Map<String,Object> response = new HashMap<>();

        Page<Teacher> teacherPage = teacherRepository.findAll(pageable);

        response.put("products",   teacherPage.getContent());
        response.put("currentPage", teacherPage.getNumber());
        response.put("totalItems", teacherPage.getTotalElements());
        response.put("totalPages", teacherPage.getTotalPages());

        return response;
    }

    public Teacher getTeacherById(Long id){
        return teacherRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Teacher not found with id: " + id));
    }

    public List<Teacher> getTeacherBySubject(String subject){
        List<Teacher> teachers = teacherRepository.findBySubject(subject);
        return teachers;
    }



    public List<Teacher> getTeacherByFirstname(String firstname){
        List<Teacher> teachers = teacherRepository.findByFirstname(firstname);
        return teachers;
    }

    @Transactional
    public Teacher createTeacher(Teacher teacher){
        return teacherRepository.save(teacher);
    }

    @Transactional
    public Teacher updateTeacher(Long id, Teacher teacher){
        Teacher updateTeacher = getTeacherById(id);

        updateTeacher.setFirstname(teacher.getFirstname());
        updateTeacher.setLastname(teacher.getLastname());
        updateTeacher.setEmail(teacher.getEmail());
        updateTeacher.setPhoneNumber(teacher.getPhoneNumber());
        updateTeacher.setSubject(teacher.getSubject());
        updateTeacher.setSalary(teacher.getSalary());

        return teacherRepository.save(updateTeacher);
    }

    @Transactional
    public void deleteTeacher(Long id){
       Teacher teacher = getTeacherById(id);
       teacherRepository.delete(teacher);
    }
}
