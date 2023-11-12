package com.example.NoteApplication.controller;

import com.example.NoteApplication.DTO.NoteDto;
import com.example.NoteApplication.DTO.constants.Views;
import com.example.NoteApplication.controller.interfaces.NoteController;
import com.example.NoteApplication.service.interfaces.NoteService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/note")
@Tag(name = "Notes", description = "Notes management APIs")
public class NoteControllerImpl implements NoteController {

    private final NoteService noteService;

    @Operation(
            summary = "Find a Note by Id",
            description = "Get a Note object by specifying its id.")
    @ApiResponse(
            responseCode = "200",
            content = {
                    @Content(schema = @Schema(implementation = NoteDto.class), mediaType = "application/json")})
    @GetMapping("/{id}")
    @Override
    public final NoteDto getNote(@Parameter(description = "Note Id.", example = "1") @PathVariable Long id) {
        log.debug("Getting a note with id: {}", id);
        return noteService.findById(id);
    }

    @Operation(
            summary = "Find all notes in database",
            description = "Getting all notes from database"
    )
    @ApiResponse(
            responseCode = "200",
            content = {
                    @Content(schema = @Schema(implementation = NoteDto.class), mediaType = "application/json")})
    @GetMapping
    @Override
    public final List<NoteDto> getAllNotes() {
        log.debug("Getting all notes");
        return noteService.findAll();
    }

    @Override
    @Operation(
            summary = "Find all notes by name",
            description = "Get all notes from database with matching name"
    )
    @GetMapping("/findByName")
    public List<NoteDto> searchNotesByName(@RequestParam String name) {
        log.debug("Searching notes by name: {}", name);
        return noteService.searchNotesByName(name);
    }
    @Operation(
            summary = "Find all notes by course id",
            description = "Get all notes from database with matching course id"
    )
    @GetMapping("/getByCourseId/{courseId}")
    public List<NoteDto> searchNotesByCourseId(@PathVariable Long courseId){
        log.debug("Searching notes by course id: {}", courseId);
        return noteService.searchNotesByCourseId(courseId);
    }

    @Operation(
            summary = "Create a Note",
            description = "Create a Note object.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(Views.Post.class)
    @Override
    public final NoteDto createNote(@Valid @RequestBody @JsonView(value = Views.Post.class) NoteDto note) {
        log.debug("Creating note: {}", note);
        return noteService.createNote(note);
    }

    @Operation(
            summary = "Delete note by its id",
            description = "Delete note by its id"
    )
    @DeleteMapping("/{id}")
    @Override
    public final NoteDto deleteNote(
            @Parameter(description = "Note id", example = "1") @PathVariable Long id) {
        log.debug("Deleting a note: {}", id);
        return noteService.deleteById(id);
    }

    @Operation(
            summary = "Update a note by its id",
            description = "Update a note in repository"
    )
    @PatchMapping("/{id}")
    @Override
    public final NoteDto updateNote(
            @Parameter(description = "Note id", example = "1") @PathVariable Long id,
            @RequestBody @JsonView(Views.Patch.class) NoteDto noteDto) {
        log.debug("Updating a note with id: {}", id);
        return noteService.updateNote(id, noteDto);
    }

}
