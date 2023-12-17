package com.example.NoteApplication.DTO;

import com.example.NoteApplication.DTO.constants.Views;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import static com.example.NoteApplication.DTO.constants.DtoConstants.NAME_NOT_BLANK_MESSAGE;
import static com.example.NoteApplication.DTO.constants.DtoConstants.NAME_NOT_NULL_MESSAGE;

@Getter
@Setter
@Jacksonized
@SuperBuilder
public class CourseDto {

    @Schema(description = "course id", example = "1")
    @JsonView()
    Long id;
    @Schema(description = "Course name", example = "English")
    @JsonView({Views.Get.class, Views.Patch.class, Views.Post.class})
    @NotNull(message = NAME_NOT_NULL_MESSAGE)
    @NotBlank(message = NAME_NOT_BLANK_MESSAGE)
    String name;

    @JsonView({Views.Get.class})
    String createdBy;
}
