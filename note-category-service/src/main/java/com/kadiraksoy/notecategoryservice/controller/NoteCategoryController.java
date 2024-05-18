package com.kadiraksoy.notecategoryservice.controller;

import com.kadiraksoy.filecategory.exception.UserNotExistException;
import com.kadiraksoy.filecategory.exception.NoteCategoryNotFoundException;
import com.kadiraksoy.notecategoryservice.dto.NoteCategoryRequest;
import com.kadiraksoy.notecategoryservice.dto.NoteCategoryResponse;
import com.kadiraksoy.notecategoryservice.service.NoteCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/note-categories")
public class NoteCategoryController {

    private final NoteCategoryService noteCategoryService;

    public NoteCategoryController(NoteCategoryService noteCategoryService) {
        this.noteCategoryService = noteCategoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<NoteCategoryResponse> createNoteCategory(@RequestBody NoteCategoryRequest noteCategoryRequest) {
        try {
            NoteCategoryResponse noteCategoryResponse = noteCategoryService.createNoteCategory(noteCategoryRequest);
            return new ResponseEntity<>(noteCategoryResponse, HttpStatus.CREATED);
        } catch (UserNotExistException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<NoteCategoryResponse> updateNoteCategory(@PathVariable Long id, @RequestBody NoteCategoryRequest noteCategoryRequest) {
        try {
            NoteCategoryResponse noteCategoryResponse = noteCategoryService.updateNoteCategory(id, noteCategoryRequest);
            return new ResponseEntity<>(noteCategoryResponse, HttpStatus.OK);
        } catch (NoteCategoryNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteNoteCategory(@PathVariable Long id) {
        try {
            noteCategoryService.deleteNoteCategory(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoteCategoryNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<NoteCategoryResponse> getNoteCategoryById(@PathVariable Long id) {
        try {
            NoteCategoryResponse noteCategoryResponse = noteCategoryService.getNoteCategoryById(id);
            return new ResponseEntity<>(noteCategoryResponse, HttpStatus.OK);
        } catch (NoteCategoryNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<NoteCategoryResponse>> getAllCategory() {
        List<NoteCategoryResponse> noteCategoryResponseList = noteCategoryService.getAllCategory();
        return new ResponseEntity<>(noteCategoryResponseList, HttpStatus.OK);
    }




}
