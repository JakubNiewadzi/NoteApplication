package com.example.NoteApplication.controller.interfaces;

import com.example.NoteApplication.DTO.CourseDto;

import java.util.List;

public interface CourseController {

    public List<CourseDto> getCourses();

    CourseDto getCourseById(Long id);

    CourseDto createCourse(CourseDto courseDto);

    CourseDto deleteCourse(Long id);

    CourseDto updateCourse(Long id, CourseDto courseDto);
}
