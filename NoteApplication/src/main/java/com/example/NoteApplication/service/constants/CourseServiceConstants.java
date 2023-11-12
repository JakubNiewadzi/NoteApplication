package com.example.NoteApplication.service.constants;

public final class CourseServiceConstants {
    private CourseServiceConstants() {
    }

    public static final String COURSE_NOT_FOUND = "No course with given id has been found %d";
    public static final String COURSE_ALREADY_IN_DATABASE = "Course with given name already exists in database: %s";
    public static final String COURSE_DTO_HAS_ID = "Provide a course dto without specified id";
}
