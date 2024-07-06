package com.prashanth.spring_boot_neo4j.controllers;

import com.prashanth.spring_boot_neo4j.requests.CourseEnrollmentRequest;
import com.prashanth.spring_boot_neo4j.services.CourseEnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/enrollments")
public class CourseEnrollmentController {

    private final CourseEnrollmentService courseEnrollmentService;

    public CourseEnrollmentController(CourseEnrollmentService courseEnrollmentService) {
        this.courseEnrollmentService = courseEnrollmentService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllEnrollmentsByUsername(Principal principal){
        return ResponseEntity.ok(courseEnrollmentService.getAllEnrolledCoursesByUsername(principal.getName()));
    }

    @PostMapping("/")
    public ResponseEntity<?> enrollIn(@RequestBody CourseEnrollmentRequest request, Principal principal) {
        return courseEnrollmentService.enrollIn(principal.getName(), request.getCourseIdentifier());
    }
}
