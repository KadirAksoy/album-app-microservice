package com.kadiraksoy.userservice.service;


import com.kadiraksoy.userservice.dto.UserRequest;
import com.kadiraksoy.userservice.dto.UserResponse;
import com.kadiraksoy.userservice.exception.EmailAlreadyExisting;
import com.kadiraksoy.userservice.exception.EmailNotFoundException;
import com.kadiraksoy.userservice.exception.UserNotFoundException;
import com.kadiraksoy.userservice.mapper.UserMapper;
import com.kadiraksoy.userservice.model.User;
import com.kadiraksoy.userservice.producer.KafkaProducer;
import com.kadiraksoy.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final KafkaProducer kafkaProducer;

    public UserService(UserRepository userRepository, UserMapper userMapper, KafkaProducer kafkaProducer) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.kafkaProducer = kafkaProducer;
    }
    public UserResponse createUser(UserRequest userRequest){
        User user = userMapper.userRequestToEntity(userRequest);
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new EmailAlreadyExisting("Email already existing.");
        }

        userRepository.save(user);

        kafkaProducer.produceData(userRequest);

        return userMapper.userRequestToUserResponse(userRequest);
    }

    public UserResponse updateUser(Long id,UserRequest userRequest){
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){

            optionalUser.get().setEmail(userRequest.getFirstName());
            optionalUser.get().setEmail(userRequest.getLastName());
            optionalUser.get().setEmail(userRequest.getEmail());
            optionalUser.get().setPassword(userRequest.getPassword());
        }else {
            throw new UserNotFoundException("User not found with id:" + id);
        }
        return userMapper.userRequestToUserResponse(userRequest);
    }
    public void deleteUser(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("User not found with id:" + id);
        }
        userRepository.deleteById(id);
    }

    public List<UserResponse> getAllUsers(){
        List<User> userList = userRepository.findAll();
        List<UserResponse> userResponses = userList.stream()
                .map(user -> userMapper.entityToUserResponse(user))
                .collect(Collectors.toList());
        return userResponses;
    }

    public UserResponse getUserById(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("User not found with id:" + id);
        }

        return userMapper.entityToUserResponse(optionalUser.get());
    }

    public UserResponse getUserByEmail(String email){
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(!optionalUser.isPresent()){
            throw new EmailNotFoundException("User not found with email:" + email);
        }
        return userMapper.entityToUserResponse(optionalUser.get());

    }





}
