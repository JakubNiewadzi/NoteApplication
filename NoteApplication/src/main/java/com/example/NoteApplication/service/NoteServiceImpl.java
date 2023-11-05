package com.example.NoteApplication.service;

import com.example.NoteApplication.DTO.NoteDto;
import com.example.NoteApplication.entity.Note;
import com.example.NoteApplication.mapper.NoteMapper;
import com.example.NoteApplication.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

import static com.example.NoteApplication.service.constants.ServiceConstants.NOTE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    @Override
    public final NoteDto findById(Long id) {
        return noteRepository.findById(id)
                .map(noteMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, NOTE_NOT_FOUND));
    }

    @Override
    public final List<NoteDto> findAll() {
        return noteRepository.findAll()
                .stream()
                .map(noteMapper::toDto)
                .toList();
    }

    @Override
    public final NoteDto createNote(NoteDto noteDto) {
        if (noteDto.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provide note without id");
        }

        Note note = noteMapper.toEntity(noteDto);
        note = noteRepository.saveAndFlush(note);
        return noteMapper.toDto(note);
    }

    @Override
    public final NoteDto deleteById(Long id) {
        NoteDto deletedNote = noteRepository.findById(id)
                .map(noteMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, NOTE_NOT_FOUND));
        noteRepository.deleteById(id);
        return deletedNote;
    }

    @Override
    public final NoteDto updateNote(Long id, NoteDto noteDto) {
        if (!Objects.equals(id, noteDto.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id and note.id is not equal");
        }
        Note note = noteMapper.toEntity(noteDto);
        note = noteRepository.saveAndFlush(note);
        return noteMapper.toDto(note);
    }

    @Override
    public List<NoteDto> searchNotesBySubject(String subject) {
        return noteRepository.findBySubject(subject)
                .stream()
                .map(noteMapper::toDto)
                .toList();
    }

}
