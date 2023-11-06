package com.example.NoteApplication.DTO;

import com.example.NoteApplication.DTO.constants.Views;
import com.example.NoteApplication.entity.Subject;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import static com.example.NoteApplication.DTO.constants.NoteDtoConstants.*;

@Getter
@Setter
@Jacksonized
@SuperBuilder
public class NoteDto {

    @Schema(description = "Note id")
    @JsonView()
    Long id;
    @Schema(description = "Note name", example = "English - first class note")
    @JsonView({Views.Get.class, Views.Patch.class, Views.Post.class})
    @NotNull(message = NAME_NOT_NULL_MESSAGE)
    @NotBlank(message = NAME_NOT_BLANK_MESSAGE)
    String name;
    @Schema(description = "Note content", example = "This is a note")
    @JsonView({Views.Get.class, Views.Patch.class, Views.Post.class})
    @NotNull(message = CONTENT_NOT_NULL_MESSAGE)
    @NotBlank(message = CONTENT_NOT_BLANK_MESSAGE)
    String content;
    @Schema(description = "Note subject", example = "ENGLISH")
    @JsonView({Views.Get.class, Views.Patch.class, Views.Post.class})
    @NotNull(message = SUBJECT_NOT_NULL_MESSAGE)
    Subject subject;

}
