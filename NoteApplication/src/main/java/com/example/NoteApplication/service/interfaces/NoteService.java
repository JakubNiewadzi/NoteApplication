package com.example.NoteApplication.service.interfaces;

import com.example.NoteApplication.DTO.NoteDto;

import java.util.List;

public interface NoteService {

    NoteDto findById(Long id);

    List<NoteDto> findAll();

    NoteDto createNote(NoteDto noteDto);

    NoteDto deleteById(Long id);

    NoteDto updateNote(Long id, NoteDto noteDto);

    List<NoteDto> searchNotesBySubject(String subject);

    List<NoteDto> searchNotesByName(String name);
}
