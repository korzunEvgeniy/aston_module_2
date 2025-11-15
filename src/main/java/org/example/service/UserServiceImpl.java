package org.example.service;

import org.example.console.model.Mapper;
import org.example.console.model.UserDto;
import org.example.dao.UserDao;
import org.example.dao.UserDaoImpl;
import org.example.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();
    @Override
    public void create(UserDto userDto) {
        userDao.save(Mapper.mappedToEntity(userDto));
    }

    @Override
    public UserDto findByEmail(String email) {
        return Mapper.mappedToDto(userDao.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User with email:" + email + " not found")));
    }

    @Override
    public List<UserDto> findAll() {
        List<UserDto> users = new ArrayList<>();
        for (User user : userDao.findAll()) {
            users.add(Mapper.mappedToDto(user));
        }
        return users;
    }

    @Override
    public void update(UserDto userDto) {
        userDao.update(Mapper.mappedToEntity(userDto));

    }

    @Override
    public void delete(String email) {
        userDao.findByEmail(email).ifPresent(userDao::delete);
    }
}
