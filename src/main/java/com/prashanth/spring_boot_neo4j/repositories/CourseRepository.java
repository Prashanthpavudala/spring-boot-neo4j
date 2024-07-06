package com.prashanth.spring_boot_neo4j.repositories;

import com.prashanth.spring_boot_neo4j.models.Course;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends Neo4jRepository<Course, Long> {

    Course findCourseByIdentifier(String identifier);

    @Query("MATCH (:User {username:$username})-[:ENROLLED_IN]->(courses:Course) RETURN courses")
    List<Course> findAllEnrolledCoursesByUsername(String username);
}
