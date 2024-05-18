package com.kadiraksoy.notecategoryservice.repository;


import com.kadiraksoy.notecategoryservice.model.NoteCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteCategoryRepository extends JpaRepository<NoteCategory, Long> {
}
