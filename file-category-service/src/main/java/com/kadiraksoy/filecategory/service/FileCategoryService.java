package com.kadiraksoy.filecategory.service;


import com.kadiraksoy.filecategory.dto.FileCategoryRequest;
import com.kadiraksoy.filecategory.dto.FileCategoryResponse;
import com.kadiraksoy.filecategory.dto.UserResponse;
import com.kadiraksoy.filecategory.exception.FileCategoryNotFoundException;
import com.kadiraksoy.filecategory.exception.UserNotExistException;
import com.kadiraksoy.filecategory.mapper.FileCategoryMapper;
import com.kadiraksoy.filecategory.model.FileCategory;
import com.kadiraksoy.filecategory.repository.FileCategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FileCategoryService {

    private final FileCategoryRepository fileCategoryRepository;
    private  APIClient apiClient;
    private final FileCategoryMapper fileCategoryMapper;

    public FileCategoryService(FileCategoryRepository fileCategoryRepository, APIClient apiClient, FileCategoryMapper fileCategoryMapper) {
        this.fileCategoryRepository = fileCategoryRepository;
        this.apiClient = apiClient;
        this.fileCategoryMapper = fileCategoryMapper;
    }

    public FileCategoryResponse createFileCategory(FileCategoryRequest fileCategoryRequest){
        FileCategory fileCategory = fileCategoryMapper.fileCategoryRequestToEntity(fileCategoryRequest);
        UserResponse userResponse = apiClient.getUser(fileCategoryRequest.getUserId());
        if(userResponse.getId() != null){
            fileCategoryRepository.save(fileCategory);
            log.info("File category başarıyla eklendi");

            return fileCategoryMapper.fileCategoryRequestToFileCategoryResponse(fileCategoryRequest);
        }
        throw new UserNotExistException("User not exist");

    }

    public FileCategoryResponse updateFileCategory(Long id, FileCategoryRequest fileCategoryRequest){
        Optional<FileCategory> optionalFileCategory = fileCategoryRepository.findById(id);
        if(optionalFileCategory.isPresent()){
            optionalFileCategory.get().setName(fileCategoryRequest.getName());
        }
        else{
            throw new FileCategoryNotFoundException("File category not found with id:" + id);
        }
        fileCategoryRepository.save(optionalFileCategory.get());
        log.info("file category updated");

        return fileCategoryMapper.entityToFileCategoryResponse(optionalFileCategory.get());
    }

    public void deleteFileCategory(Long id){
        Optional<FileCategory> optionalFileCategory = fileCategoryRepository.findById(id);
        if(optionalFileCategory.isEmpty()){
            throw new FileCategoryNotFoundException("File category not found with id:" + id);
        }
        fileCategoryRepository.deleteById(id);
        log.info("file category deleted.");
    }

    public FileCategoryResponse getFileCategoryById(Long id){
        Optional<FileCategory> optionalFileCategory = fileCategoryRepository.findById(id);
        if(optionalFileCategory.isEmpty()){
            throw new FileCategoryNotFoundException("File category not found with id:" + id);
        }
        return fileCategoryMapper.entityToFileCategoryResponse(optionalFileCategory.get());
    }

    public List<FileCategoryResponse> getAllFileCategory(){
        List<FileCategory> fileCategoryList = fileCategoryRepository.findAll();
        List<FileCategoryResponse> fileCategoryResponseList = fileCategoryList.stream()
                .map(fileCategoryMapper::entityToFileCategoryResponse)
                .collect(Collectors.toList());

        return fileCategoryResponseList;
    }
}
