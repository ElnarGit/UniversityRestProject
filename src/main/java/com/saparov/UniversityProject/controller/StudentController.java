package com.saparov.UniversityProject.controller;


import com.saparov.UniversityProject.entity.Student;
import com.saparov.UniversityProject.enums.Faculty;
import com.saparov.UniversityProject.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ){
       Pageable pageable = PageRequest.of(page,size);

       return new ResponseEntity<>(
               studentService.getAllStudents(pageable),
               HttpStatus.OK);
    }

    @GetMapping("/faculty")
    public ResponseEntity<List<Student>> getStudentByFaculty(@RequestParam("faculty") Faculty faculty) {
        List<Student> students = studentService.findByFaculty(faculty);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Student getStudentById(@PathVariable("id") Long id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("/firstname")
    @ResponseStatus(HttpStatus.OK)
    public Student getStudentByFirstname(@RequestParam("firstname") String firstname) {
        return studentService.getStudentByFirstname(firstname);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public Student createStudent(@RequestBody Student student){
       return studentService.createStudent(student);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public Student updateStudent(@PathVariable("id") Long id, @RequestBody Student student){
       return studentService.updateStudent(id,student);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteStudent(@PathVariable("id") Long id){
        studentService.deleteStudent(id);

        return "Student deleted";
    }
}
