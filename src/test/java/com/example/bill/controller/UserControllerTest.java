package com.example.bill.controller;


import com.example.bill.entity.Bill;
import com.example.bill.entity.Users;
import com.example.bill.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;



    @Test
    public void testGetAllBills() throws Exception {

        List<Users> mockUser = new ArrayList<>();
        mockUser.add(new Users(1L, "Rohit","rohit@gmail.com",null));

        when(userService.getAllUsers()).thenReturn(mockUser);


        mockMvc.perform(MockMvcRequestBuilders.get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userName").value("Rohit1"));


        Mockito.verify(userService, Mockito.times(1)).getAllUsers();
    }

    @Test
    public void getUserById() throws Exception
    {
        Long userId = 1L;
        Users mockUser = new Users(1L,"rohit","rohit@gmail.com",null);

        when(userService.getUserById(userId)).thenReturn(Optional.of(mockUser));


        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/{userId}",userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(userId));

        Mockito.verify(userService, Mockito.times(1)).getUserById(userId);

    }
}