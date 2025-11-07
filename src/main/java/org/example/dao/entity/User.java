package org.example.dao.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class User {

    private int id;
    private String name;
    private String email;
    private int age;
    private LocalDateTime created_at;


    public User(int id,
                String name,
                String email,
                int age,
                LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        created_at = LocalDateTime.now();
    }

    public User() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && age == user.age && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(created_at, user.created_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, age, created_at);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
