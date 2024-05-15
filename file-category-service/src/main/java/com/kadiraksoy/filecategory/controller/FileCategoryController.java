package com.kadiraksoy.filecategory.controller;

import com.kadiraksoy.filecategory.dto.FileCategoryRequest;
import com.kadiraksoy.filecategory.dto.FileCategoryResponse;
import com.kadiraksoy.filecategory.service.FileCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fileCategories")
public class FileCategoryController {

    private final FileCategoryService fileCategoryService;

    public FileCategoryController(FileCategoryService fileCategoryService) {
        this.fileCategoryService = fileCategoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<FileCategoryResponse> createFileCategory(@RequestBody FileCategoryRequest fileCategoryRequest) {
        FileCategoryResponse response = fileCategoryService.createFileCategory(fileCategoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FileCategoryResponse> updateFileCategory(@PathVariable Long id, @RequestBody FileCategoryRequest fileCategoryRequest) {
        FileCategoryResponse response = fileCategoryService.updateFileCategory(id, fileCategoryRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFileCategory(@PathVariable Long id) {
        fileCategoryService.deleteFileCategory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileCategoryResponse> getFileCategoryById(@PathVariable Long id) {
        FileCategoryResponse response = fileCategoryService.getFileCategoryById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FileCategoryResponse>> getAllFileCategories() {
        List<FileCategoryResponse> responseList = fileCategoryService.getAllFileCategory();
        return ResponseEntity.ok(responseList);
    }
}

