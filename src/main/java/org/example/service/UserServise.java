package org.example.service;

import org.example.dao.entity.User;

public interface UserServise {
    void save(User user);
    User findById(int id);
    void update(User user);
    void delete(int id);
}
