package com.kadiraksoy.notecategoryservice.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoteCategoryResponse {

    private Long id;
    private String name;
    private Long userId;
}
