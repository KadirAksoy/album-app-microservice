package com.kadiraksoy.noteservice.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "notes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {

    @Id
    private String id;
    private String title;
    private String description;
    private String noteCategoryId;
    private String userId;


}
