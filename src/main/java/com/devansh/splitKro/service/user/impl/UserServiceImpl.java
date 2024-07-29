package com.devansh.splitKro.service.user.impl;

import com.devansh.splitKro.exception.UserNotFoundException;
import com.devansh.splitKro.model.user.User;
import com.devansh.splitKro.model.user.UserRequestDto;
import com.devansh.splitKro.model.user.UserResponseDto;
import com.devansh.splitKro.repository.UserRepository;
import com.devansh.splitKro.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        userList.forEach(user -> userResponseDtos.add(new UserResponseDto(user)));
        return userResponseDtos;
    }

    @Override
    public UserResponseDto getUserById(Long userId) {
        return userRepository.findById(userId).map(UserResponseDto::new).orElse(null);
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        return new UserResponseDto(userRepository.save(new User(userRequestDto)));
    }

    @Override
    public UserResponseDto updateUser(Long userId, UserRequestDto updatedUser) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmailId(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }
}
