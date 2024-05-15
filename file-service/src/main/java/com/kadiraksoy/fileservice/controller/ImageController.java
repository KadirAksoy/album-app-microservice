package com.kadiraksoy.fileservice.controller;


import com.kadiraksoy.fileservice.exception.CategoryNotFoundException;
import com.kadiraksoy.fileservice.model.ImageData;
import com.kadiraksoy.fileservice.service.ImageService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    @Transactional
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file,
                                         @RequestParam("categoryId") Long categoryId) {
        try {
            ImageData savedImageData = imageService.saveImageData(file, categoryId);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedImageData);
        } catch (IOException | CategoryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    @Transactional
    public ResponseEntity<?> updateImage(@PathVariable("id") Long id,
                                         @RequestParam("file") MultipartFile file,
                                         @RequestParam("categoryId") Long categoryId) {
        try {
            ImageData updatedImageData = imageService.updateImageData(id, file, categoryId);
            return ResponseEntity.ok(updatedImageData);
        } catch (IOException | RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable("id") Long id) {
        imageService.deleteImageData(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getImage(@PathVariable("id") Long id) {
        try {
            byte[] imageData = imageService.getImageData(id);
            return ResponseEntity.ok(imageData);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
