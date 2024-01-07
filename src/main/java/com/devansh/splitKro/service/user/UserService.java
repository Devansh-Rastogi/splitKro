package com.devansh.splitKro.service.user;

import com.devansh.splitKro.model.user.UserRequestDto;
import com.devansh.splitKro.model.user.UserResponseDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserById(Long userId);
    UserResponseDto createUser(UserRequestDto user);
    UserResponseDto updateUser(Long userId, UserRequestDto updatedUser);
    void deleteUser(Long userId);

    UserDetailsService userDetailsService();
}
