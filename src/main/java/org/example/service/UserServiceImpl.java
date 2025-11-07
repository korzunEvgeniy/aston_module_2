package org.example.service;

import org.example.dao.UserDao;
import org.example.dao.UserDaoImpl;
import org.example.dao.entity.User;

public class UserServiceImpl implements UserServise{

    private final UserDao userDao = new UserDaoImpl();
    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public void update(User user) {
        userDao.update(user);

    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }
}
