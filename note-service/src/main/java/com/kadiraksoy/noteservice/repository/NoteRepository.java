package com.kadiraksoy.noteservice.repository;

import com.kadiraksoy.noteservice.model.Note;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends ElasticsearchRepository<Note, String> {
}