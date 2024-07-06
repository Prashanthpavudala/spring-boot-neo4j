package com.prashanth.spring_boot_neo4j.services;

import com.prashanth.spring_boot_neo4j.dto.CourseDTO;
import com.prashanth.spring_boot_neo4j.models.Course;
import com.prashanth.spring_boot_neo4j.models.Lesson;
import com.prashanth.spring_boot_neo4j.repositories.CourseRepository;
import com.prashanth.spring_boot_neo4j.repositories.LessonRepository;
import com.prashanth.spring_boot_neo4j.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final LessonService lessonService;
    private final UserRepository userRepository;

    public CourseService(CourseRepository courseRepository, LessonService lessonService, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.lessonService = lessonService;
        this.userRepository = userRepository;
    }

    public List<CourseDTO> getAllCourses(Principal principal){
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(
                (course -> {
                    CourseDTO courseDTO = new CourseDTO();
                    BeanUtils.copyProperties(course, courseDTO);
                    courseDTO.setLessons(lessonService.getAllLessonsByCourseIdentifier(course.getIdentifier()));
                    if(principal!=null){
                        courseDTO.setIsEnrolled(userRepository.findEnrollmentStatus(principal.getName(), course.getIdentifier()));
                    }
                    return courseDTO;
                })
        ).collect(Collectors.toList());
    }

    public CourseDTO getCourseByIdentifier(String identifier){
        Course course = courseRepository.findCourseByIdentifier(identifier);
        if(course!=null){
            CourseDTO courseDTO = new CourseDTO();
            BeanUtils.copyProperties(course, courseDTO);
            courseDTO.setLessons(lessonService.getAllLessonsByCourseIdentifier(course.getIdentifier()));
            return courseDTO;
        }
        return null;
    }
}
