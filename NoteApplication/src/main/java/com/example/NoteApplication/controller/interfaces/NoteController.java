package com.example.NoteApplication.controller.interfaces;

import com.example.NoteApplication.DTO.NoteDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface NoteController {

    NoteDto getNote(Long id);

    List<NoteDto> getAllNotes();

    List<NoteDto> searchNotesByName(String name);
    List<NoteDto> searchNotesByCourseId(Long courseId);

    NoteDto createNote(NoteDto note);

    NoteDto deleteNote(Long id);

    NoteDto updateNote(Long id, NoteDto noteDto);

}
