package com.kadiraksoy.filecategory.repository;

import com.kadiraksoy.filecategory.model.FileCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileCategoryRepository extends JpaRepository<FileCategory, Long> {
}
