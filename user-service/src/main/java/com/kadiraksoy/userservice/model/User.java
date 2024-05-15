package com.kadiraksoy.userservice.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "FirstName may not be null")
    @Size(min = 2, max = 25)
    private String firstName;
    @NotEmpty(message = "LastName may not be null")
    @Size(min = 2, max = 25)
    private String lastName;
    @NotEmpty(message = "Email may not be null")
    @Size(min = 8, max = 50)
    private String email;
    @NotEmpty(message = "Password may not be null")
    @Size(min = 12, max = 30)
    private String password;

}
