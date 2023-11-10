package com.example.NoteApplication.service;

import com.example.NoteApplication.DTO.CourseDto;
import com.example.NoteApplication.entity.Course;
import com.example.NoteApplication.exception.CourseAlreadyInDatabaseException;
import com.example.NoteApplication.exception.CourseDtoHasIdException;
import com.example.NoteApplication.exception.CourseNotFoundException;
import com.example.NoteApplication.mapper.CourseMapper;
import com.example.NoteApplication.repository.CourseRepository;
import com.example.NoteApplication.service.interfaces.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.NoteApplication.service.constants.CourseServiceConstants.*;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Override
    public List<CourseDto> getCourses() {
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toDto)
                .toList();
    }

    @Override
    public CourseDto getCourseById(Long id) {
        return courseRepository.findById(id)
                .map(courseMapper::toDto)
                .orElseThrow(() -> new CourseNotFoundException(
                        String.format(COURSE_NOT_FOUND, id)));
    }

    @Override
    public CourseDto addCourse(CourseDto courseDto) {
        if (courseDto.getId() != null) {
            throw new CourseDtoHasIdException(COURSE_DTO_HAS_ID);
        }

        checkCourseByName(courseDto.getName());
        Course course = courseMapper.toEntity(courseDto);
        course = courseRepository.saveAndFlush(course);
        return courseMapper.toDto(course);
    }

    @Override
    public CourseDto deleteCourse(Long id) {
        CourseDto courseDto = courseRepository.findById(id)
                .map(courseMapper::toDto)
                .orElseThrow(() -> new CourseNotFoundException(
                        String.format(COURSE_NOT_FOUND, id)));
        courseRepository.deleteById(id);
        return courseDto;
    }

    @Override
    public CourseDto updateNote(Long id, CourseDto courseDto) {
        Course oldCourse = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(
                        String.format(COURSE_NOT_FOUND, id)));
        checkCourseByName(courseDto.getName());
        Course course = courseMapper.toEntity(courseDto);
        course.setId(oldCourse.getId());

        course = courseRepository.saveAndFlush(course);
        return courseMapper.toDto(course);
    }

    private void checkCourseByName(String name){
        if(courseRepository.findByLowercaseName(name).isPresent()){
            throw new CourseAlreadyInDatabaseException(String.format(
                    COURSE_ALREADY_IN_DATABASE, name
            ));
        }
    }

}
