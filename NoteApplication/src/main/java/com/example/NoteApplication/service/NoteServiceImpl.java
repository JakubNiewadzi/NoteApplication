package com.example.NoteApplication.service;

import com.example.NoteApplication.DTO.NoteDto;
import com.example.NoteApplication.entity.Note;
import com.example.NoteApplication.exception.NoteDtoHasIdException;
import com.example.NoteApplication.exception.NoteNotFoundException;
import com.example.NoteApplication.mapper.NoteMapper;
import com.example.NoteApplication.repository.NoteRepository;
import com.example.NoteApplication.service.interfaces.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.NoteApplication.service.constants.NoteServiceConstants.*;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    @Override
    public final NoteDto findById(Long id) {
        return noteRepository.findById(id)
                .map(noteMapper::toDto)
                .orElseThrow(() -> new NoteNotFoundException(String.format(NOTE_NOT_FOUND, id)));
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
            throw new NoteDtoHasIdException(NOTE_DTO_HAS_ID);
        }

        Note note = noteMapper.toEntity(noteDto);
        note = noteRepository.saveAndFlush(note);
        return noteMapper.toDto(note);
    }

    @Override
    public final NoteDto deleteById(Long id) {
        NoteDto deletedNote = noteRepository.findById(id)
                .map(noteMapper::toDto)
                .orElseThrow(() -> new NoteNotFoundException(String.format(NOTE_NOT_FOUND, id)));
        noteRepository.deleteById(id);
        return deletedNote;
    }

    @Override
    public final NoteDto updateNote(Long id, NoteDto noteDto) {
        Note oldNote = noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException(String.format(NOTE_NOT_FOUND, id)));

        Note note = noteMapper.toEntity(noteDto);
        note.setId(oldNote.getId());

        note = noteRepository.saveAndFlush(note);
        return noteMapper.toDto(note);
    }

    @Override
    public List<NoteDto> searchNotesByName(String name) {
        return noteRepository.findByName(name)
                .stream()
                .map(noteMapper::toDto)
                .toList();
    }

    @Override
    public List<NoteDto> searchNotesByCourseId(Long courseId) {
        return noteRepository.findByCourseId(courseId)
                .stream()
                .map(noteMapper::toDto)
                .toList();
    }

}
