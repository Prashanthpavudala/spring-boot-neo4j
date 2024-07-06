package com.prashanth.spring_boot_neo4j.repositories;

import com.prashanth.spring_boot_neo4j.models.User;
import com.prashanth.spring_boot_neo4j.queryResults.CourseEnrollQueryResult;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserRepository extends Neo4jRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query("MATCH (user:User), (course:Course) WHERE user.username = $username AND course.identifier = $identifier " +
            "RETURN EXISTS((user)-[:ENROLLED_IN]->(course))")
    Boolean findEnrollmentStatus(String username, String identifier);

    @Query("MATCH (user:User), (course:Course) WHERE user.username = $username AND course.identifier = $identifier " +
            "CREATE (user)-[:ENROLLED_IN]->(course) RETURN user, course")
    CourseEnrollQueryResult createEnrollmentRelation(String username, String identifier);
}
