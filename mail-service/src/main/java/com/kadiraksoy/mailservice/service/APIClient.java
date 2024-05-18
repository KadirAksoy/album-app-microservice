package com.kadiraksoy.mailservice.service;


import com.kadiraksoy.mailservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(url = "http://localhost:8081", value = "USER-SERVICE")
public interface APIClient {


    @GetMapping("/api/users/all")
    List<UserDto> getAllUsers();

}
