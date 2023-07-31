package com.saparov.UniversityProject.service;

import com.saparov.UniversityProject.entity.Faculty;
import com.saparov.UniversityProject.exception.NotFoundException;
import com.saparov.UniversityProject.repository.FacultyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public Map<String, Object> getAllFaculty(Pageable pageable){
        Map<String, Object> response = new HashMap<>();
        Page<Faculty> facultyPagePage = facultyRepository.findAll(pageable);

        response.put("products",   facultyPagePage.getContent());
        response.put("currentPage", facultyPagePage.getNumber());
        response.put("totalItems", facultyPagePage.getTotalElements());
        response.put("totalPages", facultyPagePage.getTotalPages());

        return response;
    }

    public Faculty getFacultyById(Long id){
        return facultyRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Faculty not found with id: " + id));
    }

    @Transactional
    public Faculty createFaculty(Faculty faculty){
        return facultyRepository.save(faculty);
    }

    @Transactional
    public Faculty updateFaculty(Long id, Faculty faculty){
        Faculty updateFaculty = getFacultyById(id);
        updateFaculty.setFacultyName(faculty.getFacultyName());
        return facultyRepository.save(updateFaculty);
    }

    @Transactional
    public void deleteFaculty(Long id){
        Faculty faculty = getFacultyById(id);
        facultyRepository.delete(faculty);
    }
}
