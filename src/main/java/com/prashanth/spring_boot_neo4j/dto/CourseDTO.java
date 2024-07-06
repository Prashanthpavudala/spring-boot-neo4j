package com.prashanth.spring_boot_neo4j.dto;

import com.prashanth.spring_boot_neo4j.models.Lesson;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseDTO {

    String identifier;
    String title;
    String teacher;
    Boolean isEnrolled;
    List<Lesson> lessons = new ArrayList<>();
}
