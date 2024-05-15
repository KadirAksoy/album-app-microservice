package com.kadiraksoy.fileservice.service;


import com.kadiraksoy.fileservice.dto.FileCategoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(url = "http://localhost:8083", value = "FILE-CATEGORY-SERVICE")
public interface APIClient {


    @GetMapping("/api/fileCategories/{category-id}")
    FileCategoryResponse getFileCategory(@PathVariable("category-id") Long id);
}
