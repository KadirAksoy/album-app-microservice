//package com.kadiraksoy.userservice.mapper;
//
//import com.kadiraksoy.userservice.dto.CreateUserRequest;
//import com.kadiraksoy.userservice.dto.CreateUserResponse;
//import com.kadiraksoy.userservice.model.User;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.factory.Mappers;
//
//
//@Mapper(componentModel = "spring")
//public interface UserMapper {
//
//    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
//
//    @Mapping(source = "user.firstName", target = "firstName")
//    @Mapping(source = "user.lastName", target = "lastName")
//    @Mapping(source = "user.email", target = "email")
//    @Mapping(source = "user.password", target = "password")
//    User toEntity(CreateUserRequest createUserRequest);
//
//    @Mapping(source = "user.firstName", target = "firstName")
//    @Mapping(source = "user.lastName", target = "lastName")
//    @Mapping(source = "user.email", target = "email")
//    @Mapping(source = "user.password", target = "password")
//    CreateUserResponse toCreateUserResponse(CreateUserRequest createUserRequest);
//
//}
