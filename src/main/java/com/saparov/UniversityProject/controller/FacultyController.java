package com.saparov.UniversityProject.controller;

import com.saparov.UniversityProject.entity.Faculty;
import com.saparov.UniversityProject.service.FacultyService;
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
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllFaculties(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ){
        Pageable pageable = PageRequest.of(page,size);

        return new ResponseEntity<>(
                facultyService.getAllFaculty(pageable),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Faculty getFacultyById(@PathVariable("id") Long id) {
        return facultyService.getFacultyById(id);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Faculty> createFaculty(@RequestBody @Valid Faculty faculty){
        Faculty createdFaculty = facultyService.createFaculty(faculty);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdFaculty.getId())
                .toUri();

        return ResponseEntity.created(location).body(createdFaculty);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public Faculty updateFaculty(@PathVariable("id") Long id, @RequestBody @Valid Faculty faculty){
        return facultyService.updateFaculty(id,faculty);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteFaculty(@PathVariable("id") Long id){
        facultyService.deleteFaculty(id);

        return "Faculty deleted";
    }
}
