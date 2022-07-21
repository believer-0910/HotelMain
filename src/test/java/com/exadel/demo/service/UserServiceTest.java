package com.exadel.demo.service;

import com.exadel.demo.dto.RoleDto;
import com.exadel.demo.dto.UserDto;
import com.exadel.demo.entity.Role;
import com.exadel.demo.entity.User;
import com.exadel.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper modelMapper;

    private User user;

    private UserDto userDto;

    private UserService userService;

    private User savedUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        userService = new UserService(userRepository, modelMapper);
        user = new User("", "", "", new Role());
        savedUser = new User("", "", "", new Role());
        userDto = new UserDto("", "", "", new RoleDto());

        when(modelMapper.map(userDto, User.class)).thenReturn(user);
        when(modelMapper.map(savedUser, UserDto.class)).thenReturn(userDto);
        when(userRepository.save(user)).thenReturn(savedUser);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
    }

    @Test
    void add(){
        UserDto userDtoSaved = userService.addUser(userDto);
        assertEquals("", userDtoSaved.getEmail());
    }

    @Test
    void getAll(){
        List<UserDto> allUsers = userService.getAllUsers();
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        assertNull(allUsers);
    }

    @Test
    void get(){
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);
        UserDto userDto = userService.getUser(1L);
        assertEquals("", userDto.getEmail());
    }

    @Test
    void update(){
        UserDto updatedUser = userService.updateUser(1L, userDto);
        assertEquals("", updatedUser.getEmail());
    }

    @Test
    void delete(){
        userService.deleteUser(1L);
        verify(userRepository).deleteById(1L);
    }

    @Test
    void deleteAll(){
        userService.deleteAllUsers();
        verify(userRepository).deleteAll();
    }
}
