package org.example.dao;

import org.example.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    void save(User user);
    Optional<User> findById(int id);
    List<User> findAll();
    void update(User user);
    void delete(User user);
}
