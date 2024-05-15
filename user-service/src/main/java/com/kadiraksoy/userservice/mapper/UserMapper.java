package com.kadiraksoy.userservice.mapper;


import com.kadiraksoy.userservice.dto.UserRequest;
import com.kadiraksoy.userservice.dto.UserResponse;
import com.kadiraksoy.userservice.model.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserMapper {

    public User userRequestToEntity(UserRequest userRequest){
        return User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();
    }
    public UserResponse userRequestToUserResponse(UserRequest userRequest){
        return UserResponse.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();
    }

    public  UserResponse entityToUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

}
