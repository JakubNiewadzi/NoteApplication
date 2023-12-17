package com.example.NoteApplication.mapper;

import com.example.NoteApplication.DTO.NoteDto;
import com.example.NoteApplication.entity.Note;
import com.example.NoteApplication.mapper.utils.MapperUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = MapperUtils.class)
public interface NoteMapper {

    @Mapping(target = "courseId", source = "course")
    NoteDto toDto(Note note);

    @Mapping(target = "course", source = "courseId")
    Note toEntity(NoteDto dto);

}
