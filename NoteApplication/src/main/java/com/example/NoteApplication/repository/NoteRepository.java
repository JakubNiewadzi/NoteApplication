package com.example.NoteApplication.repository;

import com.example.NoteApplication.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query(value = "SELECT * FROM Notes n WHERE n.subject ILIKE '%' || LOWER(?1) || '%'", nativeQuery = true)
    List<Note> findBySubject(String subject);

    @Query(value = "SELECT * FROM Notes n WHERE n.name ILIKE '%' || LOWER(?1) || '%'", nativeQuery = true)
    List<Note> findByName(String name);
}
