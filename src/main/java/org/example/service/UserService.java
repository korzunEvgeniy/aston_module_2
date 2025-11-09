package org.example.service;

import org.example.console.model.UserDto;

import java.util.List;

public interface UserService {
    void create(UserDto userDto);
    UserDto findByEmail(String email);
    List<UserDto> findAll();
    void update(UserDto userDto);
    void delete(String email);
}
