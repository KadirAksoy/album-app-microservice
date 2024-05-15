package com.kadiraksoy.fileservice.repository;

import com.kadiraksoy.fileservice.model.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImageRepository extends JpaRepository<ImageData, Long> {
}
