package com.kadiraksoy.notecategoryservice.service;
import com.kadiraksoy.notecategoryservice.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8081", value = "USER-SERVICE")
public interface APIClient {

    @GetMapping("/api/users/{user-id}")
    UserResponse getUser(@PathVariable("user-id") Long userId);
}
