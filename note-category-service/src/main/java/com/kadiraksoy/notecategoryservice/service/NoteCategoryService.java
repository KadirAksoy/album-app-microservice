package com.kadiraksoy.notecategoryservice.service;


import com.kadiraksoy.filecategory.exception.UserNotExistException;
import com.kadiraksoy.filecategory.exception.NoteCategoryNotFoundException;
import com.kadiraksoy.notecategoryservice.dto.NoteCategoryRequest;
import com.kadiraksoy.notecategoryservice.dto.NoteCategoryResponse;
import com.kadiraksoy.notecategoryservice.dto.UserResponse;
import com.kadiraksoy.notecategoryservice.mapper.NoteCategoryMapper;
import com.kadiraksoy.notecategoryservice.model.NoteCategory;
import com.kadiraksoy.notecategoryservice.repository.NoteCategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class NoteCategoryService {

    private final NoteCategoryRepository noteCategoryRepository;
    private final APIClient apiClient;
    private final NoteCategoryMapper noteCategoryMapper;

    public NoteCategoryService(NoteCategoryRepository noteCategoryRepository,
                               APIClient apiClient,
                               NoteCategoryMapper noteCategoryMapper) {
        this.noteCategoryRepository = noteCategoryRepository;
        this.apiClient = apiClient;
        this.noteCategoryMapper = noteCategoryMapper;
    }

    public NoteCategoryResponse createNoteCategory(NoteCategoryRequest noteCategoryRequest){
        NoteCategory noteCategory = noteCategoryMapper.noteCategoryRequestToEntity(noteCategoryRequest);
        UserResponse userResponse = apiClient.getUser(noteCategoryRequest.getUserId());
        if(userResponse.getId() != null){
            noteCategoryRepository.save(noteCategory);

            log.info("note kategori eklendi");
            return noteCategoryMapper.entityToNoteCategoryResponse(noteCategory);
        }throw new UserNotExistException("User not exist");
    }

    public NoteCategoryResponse updateNoteCategory(Long id,NoteCategoryRequest noteCategoryRequest){
        Optional<NoteCategory> optionalNoteCategory = noteCategoryRepository.findById(id);
        if(optionalNoteCategory.isPresent()){
            optionalNoteCategory.get().setName(noteCategoryRequest.getName());
            noteCategoryRepository.save(optionalNoteCategory.get());
            return noteCategoryMapper.entityToNoteCategoryResponse(optionalNoteCategory.get());
        }throw new NoteCategoryNotFoundException("not kategory bulunmadı.");
    }

    public void deleteNoteCategory(Long id){
        Optional<NoteCategory> optionalNoteCategory = noteCategoryRepository.findById(id);
        if(optionalNoteCategory.isPresent()){
            noteCategoryRepository.deleteById(id);
        }throw new NoteCategoryNotFoundException("not kategory bulunmadı.");
    }

    public NoteCategoryResponse getNoteCategoryById(Long id){
        Optional<NoteCategory> optionalNoteCategory = noteCategoryRepository.findById(id);
        if(optionalNoteCategory.isPresent()){
            return noteCategoryMapper.entityToNoteCategoryResponse(optionalNoteCategory.get());
        }throw new NoteCategoryNotFoundException("not kategory bulunmadı.");
    }

    public List<NoteCategoryResponse> getAllCategory(){
        List<NoteCategory> noteCategoryList = noteCategoryRepository.findAll();
        List<NoteCategoryResponse> noteCategoryResponseList = noteCategoryList.stream()
                .map(noteCategoryMapper::entityToNoteCategoryResponse)
                .collect(Collectors.toList());
        return noteCategoryResponseList;
    }



}
