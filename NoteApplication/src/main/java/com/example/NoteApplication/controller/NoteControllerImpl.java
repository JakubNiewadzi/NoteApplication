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
public class NoteControllerImpl implements NoteController {

    private final NoteService noteService;

    @Operation(
            summary = "Find a Note by Id",
            description = "Get a Note object by specifying its id.",
            tags = {"Get"})
    @ApiResponse(
            responseCode = "200",
            content = {
                    @Content(schema = @Schema(implementation = NoteDto.class), mediaType = "application/json")})
    @ApiResponse(
            responseCode = "404",
            content = {@Content(schema = @Schema())})
    @GetMapping("/{id}")
    @Override
    public final NoteDto getNote(@Parameter(description = "Note Id.", example = "1") @PathVariable Long id) {
        log.debug("Getting a note with id: {}", id);
        return noteService.findById(id);
    }

    @Operation(
            summary = "Find all notes in database",
            description = "Getting all notes from database",
            tags = {"Get"}
    )
    @ApiResponse(
            responseCode = "200",
            content = {
                    @Content(schema = @Schema(implementation = NoteDto.class), mediaType = "application/json")})
    @ApiResponse(
            responseCode = "404",
            content = {@Content(schema = @Schema())})
    @GetMapping
    @Override
    public final List<NoteDto> getAllNotes() {
        log.debug("Getting all notes");
        return noteService.findAll();
    }

    @Override
    @Operation(
            summary = "Find all notes by subject",
            description = "Get all notes from database with a certain subject",
            tags = {"Get"}
    )
    @GetMapping("/findBySubject")
    public final List<NoteDto> searchNotesBySubject(@RequestParam String subject) {
        return noteService.searchNotesBySubject(subject);
    }

    @Override
    @Operation(
            summary = "Find all notes by name",
            description = "Get all notes from database with matching name",
            tags = {"Get"}
    )
    @GetMapping("/findByName")
    public List<NoteDto> searchNotesByName(String name) {
        return noteService.searchNotesByName(name);
    }

    @Operation(
            summary = "Create a Note",
            description = "Create a Note object.",
            tags = {"Post"})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(value = Views.Get.class)
    @Override
    public final NoteDto create(@Valid @RequestBody @JsonView(value = Views.Post.class) NoteDto note) {
        log.debug("Creating note: {}", note);
        return noteService.createNote(note);
    }

    @Operation(
            summary = "Delete note by its id",
            description = "Delete note by its id",
            tags = {"Delete"}
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
            description = "Update a note in repository",
            tags = {"Patch"}
    )
    @PatchMapping("/{id}")
    @JsonView(Views.Patch.class)
    @Override
    public final NoteDto updateNote(
            @Parameter(description = "Note id", example = "1") @PathVariable Long id,
            @RequestBody @JsonView(Views.Patch.class) NoteDto noteDto) {
        log.debug("Updating a note with id: {}", id);
        return noteService.updateNote(id, noteDto);
    }

}
