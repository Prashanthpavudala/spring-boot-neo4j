package com.prashanth.spring_boot_neo4j.services;

import com.prashanth.spring_boot_neo4j.dto.CourseDTO;
import com.prashanth.spring_boot_neo4j.dto.CourseEnrollmentDTO;
import com.prashanth.spring_boot_neo4j.models.Course;
import com.prashanth.spring_boot_neo4j.queryResults.CourseEnrollQueryResult;
import com.prashanth.spring_boot_neo4j.repositories.CourseRepository;
import com.prashanth.spring_boot_neo4j.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseEnrollmentService {

    private final LessonService lessonService;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseEnrollmentService(LessonService lessonService, CourseRepository courseRepository, UserRepository userRepository) {
        this.lessonService = lessonService;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public Boolean getEnrollmentStatus(String username, String identifier) {
        return userRepository.findEnrollmentStatus(username, identifier);
    }

    public ResponseEntity<?> enrollIn(String username, String identifier) {
        if(this.getEnrollmentStatus(username, identifier)){
            return new ResponseEntity<>("User already enrolled in this course", HttpStatus.BAD_REQUEST);
        }
        CourseEnrollQueryResult result = userRepository.createEnrollmentRelation(username, identifier);
        CourseEnrollmentDTO response = new CourseEnrollmentDTO(result.getUser().getUsername(),
                result.getUser().getName(), result.getCourse());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public List<CourseDTO> getAllEnrolledCoursesByUsername(String username) {
        List<Course> courses = courseRepository.findAllEnrolledCoursesByUsername(username);
        return courses.stream().map(
                course -> {
                    CourseDTO dto = new CourseDTO();
                    BeanUtils.copyProperties(course, dto);
                    dto.setLessons(lessonService.getAllLessonsByCourseIdentifier(course.getIdentifier()));
                    dto.setIsEnrolled(true);
                    return dto;
                }
        ).collect(Collectors.toList());
    }
}
