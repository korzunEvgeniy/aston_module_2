package org.example.dao;

import org.example.dao.entity.User;

public interface UserDao {
    void save(User user);
    User findById(int id);
    void update(User user);
    void delete(int id);
}
