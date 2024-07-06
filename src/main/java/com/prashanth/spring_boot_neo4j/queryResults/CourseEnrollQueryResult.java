package com.prashanth.spring_boot_neo4j.queryResults;

import com.prashanth.spring_boot_neo4j.models.Course;
import com.prashanth.spring_boot_neo4j.models.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseEnrollQueryResult {

    User user;
    Course course;
}
