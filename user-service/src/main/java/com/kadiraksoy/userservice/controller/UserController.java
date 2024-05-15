package com.kadiraksoy.userservice.controller;

import com.kadiraksoy.userservice.dto.CreateUserRequest;
import com.kadiraksoy.userservice.dto.CreateUserResponse;
import com.kadiraksoy.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createUser")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest createUserRequest){
        CreateUserResponse createUserResponse = userService.createUser(createUserRequest);
        return ResponseEntity.ok(createUserResponse);
    }


}
