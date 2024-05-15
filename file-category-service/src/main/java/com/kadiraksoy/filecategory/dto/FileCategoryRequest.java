package com.kadiraksoy.filecategory.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileCategoryRequest {

    private String name;
    private Long userId;
}
