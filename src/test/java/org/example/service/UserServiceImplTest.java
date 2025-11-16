package org.example.service;

import org.example.console.model.Mapper;
import org.example.dao.UserDao;
import org.example.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock
    private UserDao userDao;

    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl();
    }

    @Test
    public void createTest() {
        User user = new User();
        user.setName("Andy");
        user.setEmail("andy@gmail.com");
        user.setAge(27);

        doNothing().when(userDao).save(user);

        userService.create(Mapper.toDto(user));

        verify(userDao, times(1)).save(user);
    }

    @Test
    public void findByEmailTest() {
        User user = new User();
        user.setEmail("john@gmail.com");
        when(userDao.findByEmail("john@gmail.com")).thenReturn(Optional.of(user));

        User result = Mapper.toEntity(userService.findByEmail("john@gmail.com"));
        assertNotNull(result);
        assertEquals("john@gmail.com", result.getEmail());

        verify(userDao).findByEmail("john@gmail.com");
    }

    @Test
    public void updateTest() {
        User existingUser = new User();
        existingUser.setName("Old name");
        existingUser.setEmail("old@gmail.com");
        existingUser.setAge(30);

        User updatedUser = new User();
        updatedUser.setName("New name");
        updatedUser.setEmail("new@gmail.com");
        updatedUser.setAge(35);

        when(userDao.findByEmail("old@gmail.com")).thenReturn(Optional.of(existingUser));
        doNothing().when(userDao).update(any(User.class));

        userService.update(Mapper.toDto(updatedUser));

        verify(userDao, times(1)).findByEmail("new@gmail.com");
        verify(userDao, times(1)).update(updatedUser);
    }

    @Test
    public void updateTest_userNotFound() {
        User updatedUser = new User();
        updatedUser.setId(99);

        when(userDao.findById(99)).thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () -> {
           userService.update(Mapper.toDto(updatedUser));
        });

        assertEquals("Error updating user", exception.getMessage());

        verify(userDao, times(1)).findById(99);
        verify(userDao, times(0)).update(any());
    }

    @Test
    public void deleteTest() {
        User user = new User();
        user.setEmail("andy@gmail.com");
        userDao.save(user);

        when(userDao.findByEmail("andy@gmail.co")).thenReturn(Optional.of(user));
        doNothing().when(userDao).delete(user);

        userService.delete("andy@gmail.co");

        verify(userDao, times(1)).findByEmail("andy@gmail.co");
        verify(userDao, times(1)).delete(user);
    }
}
