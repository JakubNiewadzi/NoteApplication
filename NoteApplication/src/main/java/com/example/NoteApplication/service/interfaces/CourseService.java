package com.example.NoteApplication.service.interfaces;

import com.example.NoteApplication.DTO.CourseDto;

import java.util.List;

public interface CourseService {

    List<CourseDto> getCourses();

    CourseDto getCourseById(Long id);

    CourseDto addCourse(CourseDto courseDto);

    CourseDto deleteCourse(Long id);

    CourseDto updateNote(Long id, CourseDto courseDto);
}
