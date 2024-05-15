package com.kadiraksoy.userservice.service;


import com.kadiraksoy.userservice.dto.CreateUserRequest;
import com.kadiraksoy.userservice.dto.CreateUserResponse;
import com.kadiraksoy.userservice.exception.EmailAlreadyExisting;
import com.kadiraksoy.userservice.mapper.UserMapper;
import com.kadiraksoy.userservice.model.User;
import com.kadiraksoy.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public CreateUserResponse createUser(CreateUserRequest createUserRequest){
        User user = UserMapper.INSTANCE.toEntity(createUserRequest);
        if(userRepository.findByEmail(user.getEmail()) != null){
            throw new EmailAlreadyExisting("Bu email adresi zaten kullanımda.");
        }
        userRepository.save(user);

        return UserMapper.INSTANCE.toCreateUserResponse(createUserRequest);
    }

}
