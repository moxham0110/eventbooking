package com.pmoxham.eventbooking.service.impl;

import com.pmoxham.eventbooking.dto.UserDTO;
import com.pmoxham.eventbooking.mapper.UserMapper;
import com.pmoxham.eventbooking.model.User;
import com.pmoxham.eventbooking.repository.UserRepository;
import com.pmoxham.eventbooking.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO registerUser(UserDTO userDto) {
        //mapper
        User user = UserMapper.mapToUser(userDto, new User());

        user = userRepository.save(user);
        //todo: validation - user already exists, incorrect email etc.

        return UserMapper.mapToUserDTO(user, userDto);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
