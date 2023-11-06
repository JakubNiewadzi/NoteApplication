package com.example.NoteApplication.controller.interfaces;

import com.example.NoteApplication.DTO.NoteDto;

import java.util.List;

public interface NoteController {

    NoteDto getNote(Long id);

    List<NoteDto> getAllNotes();

    List<NoteDto> searchNotesBySubject(String subject);

    NoteDto create(NoteDto note);

    NoteDto deleteNote(Long id);

    NoteDto updateNote(Long id, NoteDto noteDto);

}
