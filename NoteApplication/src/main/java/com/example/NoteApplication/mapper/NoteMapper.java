package com.example.NoteApplication.mapper;

import com.example.NoteApplication.DTO.NoteDto;
import com.example.NoteApplication.entity.Note;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoteMapper {

    NoteDto toDto(Note note);

    Note toEntity(NoteDto dto);
}
