package org.example.console.model;

import org.example.entity.User;

public class Mapper {

    public static User mappedToEntity(UserDto userDto) {

        User entity = new User();
        entity.setName(userDto.getName());
        entity.setEmail(userDto.getEmail());
        entity.setAge(userDto.getAge());
        entity.setCreatedAt(userDto.getCreatedAt());
        return entity;
    }

    public static UserDto mappedToDto(User entity) {
        UserDto userDto = new UserDto();
        userDto.setName(entity.getName());
        userDto.setEmail(entity.getEmail());
        userDto.setAge(entity.getAge());
        userDto.setCreatedAt(entity.getCreatedAt());
        return userDto;
    }
}
