package com.kadiraksoy.notecategoryservice.mapper;


import com.kadiraksoy.notecategoryservice.dto.NoteCategoryRequest;
import com.kadiraksoy.notecategoryservice.dto.NoteCategoryResponse;
import com.kadiraksoy.notecategoryservice.model.NoteCategory;
import org.springframework.stereotype.Component;

@Component
public class NoteCategoryMapper {


    public NoteCategory noteCategoryRequestToEntity(NoteCategoryRequest noteCategoryRequest){
        return NoteCategory.builder()
                .name(noteCategoryRequest.getName())
                .userId(noteCategoryRequest.getUserId())
                .build();
    }

    public NoteCategoryResponse noteCategoryRequestToNoteCategoryResponse(NoteCategoryRequest noteCategoryRequest){
        return NoteCategoryResponse.builder()
                .name(noteCategoryRequest.getName())
                .userId(noteCategoryRequest.getUserId())
                .build();
    }

    public NoteCategoryResponse entityToNoteCategoryResponse(NoteCategory noteCategory){
        return NoteCategoryResponse.builder()
                .id(noteCategory.getId())
                .name(noteCategory.getName())
                .userId(noteCategory.getUserId())
                .build();
    }



}
