package com.example.bill.service;

import com.example.bill.entity.Users;
import com.example.bill.repository.UserRepository;
import com.example.bill.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllUsers() {

        Users user1 = new Users(1L, "Rohit Bikkad", "rohit@gmail.com", null);
        Users user2 = new Users(2L, "Rohit", "rohit12@gmail.com", null);
        List<Users> mockUsers = Arrays.asList(user1, user2);


        when(userRepositoryMock.findAll()).thenReturn(mockUsers);

        List<Users> retrievedUsers = userService.getAllUsers();

        verify(userRepositoryMock, times(1)).findAll();
        assertEquals(mockUsers.size(), retrievedUsers.size(), "Returned users should match the mocked users");
    }

    @Test
    void testGetUserById() {

        Long userId = 1L;
        Users mockUser = new Users(userId, "Rohit", "rohit@gmail.com", null);

        when(userRepositoryMock.findById(userId)).thenReturn(Optional.of(mockUser));

        Optional<Users> retrievedUser = userService.getUserById(userId);

        verify(userRepositoryMock, times(1)).findById(userId);
        assertTrue(retrievedUser.isPresent(), "User should be present");
        assertEquals(mockUser, retrievedUser.get(), "Returned user should match the mocked user");
    }

    @Test
    void testGetUserByIdNotFound() {

        Long userId = 1L;

        when(userRepositoryMock.findById(userId)).thenReturn(Optional.empty());

        Optional<Users> retrievedUser = userService.getUserById(userId);

        verify(userRepositoryMock, times(1)).findById(userId);
        assertFalse(retrievedUser.isPresent(), "User should not be present");
    }

    @Test
    void testAddUser() {

        Users newUser = new Users(null, "New User", "newuser@gmail.com", null);
        Users savedUser = new Users(1L, "New User", "newuser@gmail.com", null);

        when(userRepositoryMock.save(newUser)).thenReturn(savedUser);

        Users addedUser = userService.addUser(newUser);

        verify(userRepositoryMock, times(1)).save(newUser);
        assertEquals(savedUser, addedUser, "Added user should match the saved user");
    }

    @Test
    void testDeleteUser() {
        Long userId = 1L;

        userService.deleteUser(userId);

        verify(userRepositoryMock, times(1)).deleteById(userId);
    }

}
