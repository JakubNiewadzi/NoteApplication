package com.example.NoteApplication.controller;

import com.example.NoteApplication.DTO.CourseDto;
import com.example.NoteApplication.DTO.constants.Views;
import com.example.NoteApplication.controller.interfaces.CourseController;
import com.example.NoteApplication.service.interfaces.CourseService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Course", description = "Course management APIs")
@RequestMapping("/course")
@Slf4j
public class CourseControllerImpl implements CourseController {

    private final CourseService courseService;

    @Override
    @Operation(
            summary = "Find all courses in database",
            description = "Getting all courses from database"
    )
    @ApiResponse(
            responseCode = "200",
            content = {
                    @Content(schema = @Schema(implementation = CourseDto.class), mediaType = "application/json")})
    @GetMapping
    public List<CourseDto> getCourses() {
        log.debug("Getting all courses");
        return courseService.getCourses();
    }

    @Override
    @Operation(
            summary = "Find a course by Id",
            description = "Get a course object by specifying its id.")
    @ApiResponse(
            responseCode = "200",
            content = {
                    @Content(schema = @Schema(implementation = CourseDto.class), mediaType = "application/json")})
    @GetMapping("/{id}")
    public CourseDto getCourseById(@Parameter(description = "Course Id.", example = "1") @PathVariable Long id) {
        log.debug("Getting a course by id: {}", id);
        return courseService.getCourseById(id);
    }

    @Override
    @Operation(
            summary = "Create a course",
            description = "Create a course object.")
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(Views.Post.class)
    @PostMapping
    public CourseDto createCourse(@Valid @RequestBody CourseDto courseDto) {
        log.debug("Creating a course: {}", courseDto);
        return courseService.addCourse(courseDto);
    }

    @Override
    @Operation(
            summary = "Delete course by its id",
            description = "Delete course by its id"
    )
    @DeleteMapping("/{id}")
    public CourseDto deleteCourse(@Parameter(description = "Course Id.", example = "1") @PathVariable Long id) {
        log.debug("Deleting a course with id {}", id);
        return courseService.deleteCourse(id);
    }

    @PatchMapping("/{id}")
    @Operation(
            summary = "Update a course by its id",
            description = "Update a course in repository"
    )
    @Override
    @JsonView(Views.Patch.class)
    public CourseDto updateCourse(@Parameter(description = "Course Id.", example = "1") @PathVariable Long id,
                                  @RequestBody CourseDto courseDto) {
        log.debug("Updating a course with id: {}", id);
        return courseService.updateNote(id, courseDto);
    }
}
