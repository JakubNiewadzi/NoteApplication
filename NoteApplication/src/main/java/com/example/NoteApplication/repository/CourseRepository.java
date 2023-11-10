package com.example.NoteApplication.repository;

import com.example.NoteApplication.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course c WHERE LOWER(REPLACE(c.name, ' ', '')) = LOWER(:name)")
    Optional<Course> findByLowercaseName(@Param("name") String name);

}
