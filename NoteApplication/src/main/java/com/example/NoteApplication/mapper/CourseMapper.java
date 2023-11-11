package com.example.NoteApplication.mapper;

import com.example.NoteApplication.DTO.CourseDto;
import com.example.NoteApplication.entity.Course;
import org.aspectj.lang.annotation.After;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface CourseMapper {

    CourseDto toDto(Course note);

    Course toEntity(CourseDto dto);


}
