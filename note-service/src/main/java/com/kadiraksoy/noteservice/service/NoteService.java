package com.kadiraksoy.noteservice.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.kadiraksoy.noteservice.dto.NoteCategoryDto;
import com.kadiraksoy.noteservice.dto.SearchRequestDto;
import com.kadiraksoy.noteservice.exception.NoteCategoryNotFoundException;
import com.kadiraksoy.noteservice.exception.NoteNotFoundException;
import com.kadiraksoy.noteservice.model.Note;
import com.kadiraksoy.noteservice.repository.NoteRepository;
import com.kadiraksoy.noteservice.util.ESUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;


@Service
@Slf4j
public class NoteService {


    private final NoteRepository noteRepository;
    private final APIClient apiClient;
    private final ElasticsearchClient elasticsearchClient;


    public NoteService(NoteRepository noteRepository, APIClient apiClient, ElasticsearchClient elasticsearchClient) {
        this.noteRepository = noteRepository;
        this.apiClient = apiClient;
        this.elasticsearchClient = elasticsearchClient;
    }

    public Iterable<Note> findAll() {
        return noteRepository.findAll();
    }

    public Optional<Note> findById(String id) {
        return noteRepository.findById(id);
    }

    public Note save(Note note) {
        Optional<NoteCategoryDto> noteCategory = Optional.ofNullable(apiClient.getNoteCategoryById(note.getNoteCategoryId()));
        if(noteCategory.isPresent()){
            return noteRepository.save(note);
        }throw new NoteCategoryNotFoundException("note category not found exception");


    }
    public void deleteById(String id) {
        if(noteRepository.findById(id) != null){
            noteRepository.deleteById(id);
        }throw new NoteNotFoundException("Note not found excepiton");
    }


    //    {
//        "fieldName": ["description"],
//        "searchValue": ["Ne aramak istiyorsak"]
//    }
    public List<Note> searchNotesByFieldAndValue(SearchRequestDto searchRequestDto) {
        SearchResponse<Note> response = null;
        try {
            Supplier<Query> querySupplier = ESUtil.buildQueryForFieldAndValue(searchRequestDto.getFieldName().get(0),
                    searchRequestDto.getSearchValue().get(0));//sorgu olustur

            log.info("Elasticsearch query {}", querySupplier.toString());

            response = elasticsearchClient.search(q -> q.index("items_index")
                    .query(querySupplier.get()), Note.class);//sorguyu calistir ve cevabi alir

            log.info("Elasticsearch response: {}", response.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return extractNotesFromResponse(response);
    }
//    public Set<String> findSuggestedItemNames(String title) {
//        Query autoSuggestQuery = ESUtil.buildAutoSuggestQuery(title);
//        log.info("Elasticsearch query: {}", autoSuggestQuery.toString());
//
//        try {
//            return elasticsearchClient.search(q -> q.index("items_index").query(autoSuggestQuery), Note.class)
//                    .hits()
//                    .hits()
//                    .stream()
//                    .map(Hit::source)
//                    .map(Note::getTitle)
//                    .collect(Collectors.toSet());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
    public List<Note> extractNotesFromResponse(SearchResponse<Note> response) {
        return response
                .hits()
                .hits()
                .stream()
                .map(Hit::source)
                .collect(Collectors.toList());
    }


}