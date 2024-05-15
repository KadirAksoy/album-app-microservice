package com.kadiraksoy.fileservice.service;

import com.kadiraksoy.fileservice.dto.FileCategoryResponse;
import com.kadiraksoy.fileservice.exception.CategoryNotFoundException;
import com.kadiraksoy.fileservice.model.ImageData;
import com.kadiraksoy.fileservice.repository.ImageRepository;
import com.kadiraksoy.fileservice.util.ImageUtils;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final APIClient apiClient;

    public ImageService(ImageRepository imageRepository, APIClient apiClient) {
        this.imageRepository = imageRepository;
        this.apiClient = apiClient;
    }

    @Transactional
    public ImageData saveImageData(MultipartFile file, Long categoryId) throws IOException, CategoryNotFoundException {
        byte[] image = ImageUtils.compressImage(file.getBytes());
        FileCategoryResponse fileCategoryResponse = apiClient.getFileCategory(categoryId);
        if(fileCategoryResponse.getId() != null){
            ImageData newImageData = ImageData.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .imageData(image)
                    .categoryId(categoryId)
                    .userId(fileCategoryResponse.getUserId())
                    .build();

            return imageRepository.save(newImageData);
        }
        throw new CategoryNotFoundException("Category not found with id:" + categoryId);

    }


    @Transactional
    public ImageData updateImageData(Long id, MultipartFile file, Long categoryId) throws IOException {
        ImageData imageDataToUpdate = imageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image data not found with id: " + id));

        FileCategoryResponse fileCategoryResponse = apiClient.getFileCategory(categoryId);
        if(fileCategoryResponse.getId() != null){
            byte[] compressedImage = ImageUtils.compressImage(file.getBytes());

            imageDataToUpdate.setName(file.getOriginalFilename());
            imageDataToUpdate.setType(file.getContentType());
            imageDataToUpdate.setImageData(compressedImage);
            imageDataToUpdate.setCategoryId(categoryId);
            imageDataToUpdate.setUserId(fileCategoryResponse.getUserId());

            return imageRepository.save(imageDataToUpdate);
        }
        throw new CategoryNotFoundException("Category not found with id:" + categoryId);

    }

    public void deleteImageData(Long id) {
        imageRepository.deleteById(id);
    }

    public byte[] getImageData(Long id) {
        var compressedImage = imageRepository.findById(id);
        return ImageUtils.decompressImage(compressedImage.orElseThrow().getImageData());
    }
}
