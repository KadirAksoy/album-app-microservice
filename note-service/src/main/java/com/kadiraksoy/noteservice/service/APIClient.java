package com.kadiraksoy.noteservice.service;


import com.kadiraksoy.noteservice.dto.NoteCategoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "NOTE-CATEGORY-SERVICE", url = "http://localhost:8085")
public interface APIClient {

    @GetMapping("/note-categories/{id}")
    NoteCategoryDto getNoteCategoryById(@PathVariable("id") String id);
}
