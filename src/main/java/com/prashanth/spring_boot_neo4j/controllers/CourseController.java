package com.prashanth.spring_boot_neo4j.controllers;

import com.prashanth.spring_boot_neo4j.services.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllCourses(Principal principal){
        return ResponseEntity.ok(courseService.getAllCourses(principal));
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<?> getCourseByIdentifier(@PathVariable String identifier){
        return ResponseEntity.ok(courseService.getCourseByIdentifier(identifier));
    }
}
