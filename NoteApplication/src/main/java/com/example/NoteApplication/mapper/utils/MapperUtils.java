package com.example.NoteApplication.mapper.utils;

import com.example.NoteApplication.DTO.CourseDto;
import com.example.NoteApplication.entity.Course;
import com.example.NoteApplication.mapper.CourseMapper;
import com.example.NoteApplication.service.interfaces.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperUtils {

    private final CourseService courseService;
    private final CourseMapper courseMapper;

    public Course getCourseById(Long id){
        CourseDto courseDto = courseService.getCourseById(id);
        return courseMapper.toEntity(courseDto);
    }

    public Long getIdFromCourse(Course course){
        return course.getId();
    }

}
