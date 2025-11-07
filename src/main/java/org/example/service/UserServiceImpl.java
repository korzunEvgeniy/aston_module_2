package org.example.service;

import org.example.dao.UserDao;
import org.example.dao.UserDaoImpl;
import org.example.entity.User;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserServise{

    private final UserDao userDao = new UserDaoImpl();
    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public Optional<User> findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public void update(User user) {
        userDao.update(user);

    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }
}
