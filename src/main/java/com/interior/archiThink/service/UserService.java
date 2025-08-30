package com.interior.archiThink.service;

import com.interior.archiThink.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto register(UserDto userDTO);
    String verify(UserDto userDTO);
    UserDto getUser(Long userId);
    List<UserDto> getUserList();
    UserDto updateUser(UserDto user, Long userId);
    Boolean deleteUserById(Long userId);
}
