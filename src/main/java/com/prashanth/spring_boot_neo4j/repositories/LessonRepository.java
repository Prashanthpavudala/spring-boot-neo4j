package com.prashanth.spring_boot_neo4j.repositories;

import com.prashanth.spring_boot_neo4j.models.Lesson;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends Neo4jRepository<Lesson, Long> {

    @Query("MATCH (:Course {identifier: $identifier})<-[:BELONGS_TO]-(lessons:Lesson) RETURN lessons")
    List<Lesson> findLessonsByCourseIdentifier(String identifier);
}
