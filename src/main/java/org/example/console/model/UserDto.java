package org.example.console.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class UserDto {

    private String name;
    private String email;
    private int age;
    private final LocalDateTime createdAt;

    public UserDto(String name,
                   String email,
                   int age) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.createdAt = LocalDateTime.now();
    }

    public UserDto() {
        this.createdAt = LocalDateTime.now();
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
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

    public void setCreatedAt(LocalDateTime createdAt) {
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return age == userDto.age
                && Objects.equals(name, userDto.name)
                && Objects.equals(email, userDto.email)
                && Objects.equals(createdAt, userDto.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, age, createdAt);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "name=" + name +
                ", email='" + email + '\'' +
                '}';
    }
}
