package com.kadiraksoy.filecategory.mapper;


import com.kadiraksoy.filecategory.dto.FileCategoryRequest;
import com.kadiraksoy.filecategory.dto.FileCategoryResponse;
import com.kadiraksoy.filecategory.model.FileCategory;
import org.springframework.stereotype.Component;

@Component
public class FileCategoryMapper {


    public FileCategory fileCategoryRequestToEntity(FileCategoryRequest fileCategoryRequest){
        return FileCategory.builder()
                .name(fileCategoryRequest.getName())
                .userId(fileCategoryRequest.getUserId())
                .build();
    }

    public FileCategoryResponse fileCategoryRequestToFileCategoryResponse(FileCategoryRequest fileCategoryRequest){
        return FileCategoryResponse.builder()
                .name(fileCategoryRequest.getName())
                .userId(fileCategoryRequest.getUserId())
                .build();
    }

    public FileCategoryResponse entityToFileCategoryResponse(FileCategory fileCategory){
        return FileCategoryResponse.builder()
                .id(fileCategory.getId())
                .name(fileCategory.getName())
                .userId(fileCategory.getUserId())
                .build();
    }


}
