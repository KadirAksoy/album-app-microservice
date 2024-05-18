package com.kadiraksoy.notecategoryservice.service;


import com.kadiraksoy.notecategoryservice.dto.NoteCategoryRequest;
import com.kadiraksoy.notecategoryservice.dto.NoteCategoryResponse;
import com.kadiraksoy.notecategoryservice.mapper.NoteCategoryMapper;
import com.kadiraksoy.notecategoryservice.repository.NoteCategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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


    }


}
