package com.kadiraksoy.fileservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileCategoryResponse {

    private Long id;
    private String name;
    private Long userId;
}
