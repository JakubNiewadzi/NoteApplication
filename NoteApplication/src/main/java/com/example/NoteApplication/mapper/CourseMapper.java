package com.example.NoteApplication.mapper;

import com.example.NoteApplication.DTO.CourseDto;
import com.example.NoteApplication.entity.Course;
import org.mapstruct.Mapper;

@Mapper
public interface CourseMapper {

    CourseDto toDto(Course note);

    Course toEntity(CourseDto dto);
}
