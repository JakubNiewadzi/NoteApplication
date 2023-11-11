package com.example.NoteApplication.repository;

import com.example.NoteApplication.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query(value = "SELECT * FROM Notes n WHERE n.name ILIKE '%' || LOWER(?1) || '%'", nativeQuery = true)
    List<Note> findByName(String name);

    @Query(value = "SELECT * FROM Notes n WHERE n.course_id = :courseId", nativeQuery = true)
    List<Note> findByCourseId(@Param("courseId") Long id);
}
