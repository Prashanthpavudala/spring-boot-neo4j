package com.prashanth.spring_boot_neo4j.dto;

import com.prashanth.spring_boot_neo4j.models.Course;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseEnrollmentDTO {

    String name;
    String username;
    Course course;
}
