package com.saparov.UniversityProject.controller;


import com.saparov.UniversityProject.entity.Teacher;
import com.saparov.UniversityProject.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping()
    public ResponseEntity<Map<String, Object>> getAllTeachers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ){
        Pageable pageable = PageRequest.of(page,size);

        return new ResponseEntity<>(teacherService.getAllTeachers(pageable),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Teacher getTeacherById(@PathVariable("id")Long id){
        return teacherService.getTeacherById(id);
    }

    @GetMapping("/subject")
    @ResponseStatus(HttpStatus.OK)
    public List<Teacher> findBySubject(@RequestParam("subject") String subject){
        return teacherService.getTeacherBySubject(subject);
    }

    @GetMapping("/firstname")
    @ResponseStatus(HttpStatus.OK)
    public List<Teacher> getTeacherByFirstname(@RequestParam("firstname")String firstname){
        return teacherService.getTeacherByFirstname(firstname);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Teacher> createTeacher(@RequestBody @Valid Teacher teacher){
        Teacher createdTeacher = teacherService.createTeacher(teacher);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdTeacher.getId())
                .toUri();

        return ResponseEntity.created(location).body(createdTeacher);

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public Teacher updateTeacher(@PathVariable("id") Long id, @RequestBody @Valid Teacher teacher){
        return teacherService.updateTeacher(id,teacher);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteTeacher(@PathVariable("id") Long id){
       teacherService.deleteTeacher(id);

       return "Teacher deleted";
    }
}
