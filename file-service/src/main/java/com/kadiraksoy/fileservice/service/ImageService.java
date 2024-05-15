package com.kadiraksoy.fileservice.service;

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

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Transactional
    public ImageData saveImageData(MultipartFile file, Long categoryId, Long userId) throws IOException {
        byte[] image = ImageUtils.compressImage(file.getBytes());

        ImageData newImageData = ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(image)
                .categoryId(categoryId)
                .userId(userId)
                .build();

        return imageRepository.save(newImageData);
    }


    @Transactional
    public ImageData updateImageData(Long id, MultipartFile file, Long categoryId, Long userId) throws IOException {
        ImageData imageDataToUpdate = imageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image data not found with id: " + id));

        byte[] compressedImage = ImageUtils.compressImage(file.getBytes());

        imageDataToUpdate.setName(file.getOriginalFilename());
        imageDataToUpdate.setType(file.getContentType());
        imageDataToUpdate.setImageData(compressedImage);
        imageDataToUpdate.setCategoryId(categoryId);
        imageDataToUpdate.setUserId(userId);

        return imageRepository.save(imageDataToUpdate);
    }

    public void deleteImageData(Long id) {
        imageRepository.deleteById(id);
    }

    public byte[] getImageData(Long id) {
        var compressedImage = imageRepository.findById(id);
        return ImageUtils.decompressImage(compressedImage.orElseThrow().getImageData());
    }
}
