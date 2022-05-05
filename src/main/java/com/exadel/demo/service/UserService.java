package com.exadel.demo.service;

import com.exadel.demo.dto.UserDto;
import com.exadel.demo.entity.User;
import com.exadel.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    public UserService(UserRepository userRepository, ModelMapper modelMapper, RoleService roleService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserDto addUser(UserDto userDto) {
        return modelMapper.map(userRepository.save(modelMapper.map(userDto, User.class)), UserDto.class);
    }

    public UserDto getUser(Long id) {
        return modelMapper.map(userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found")), UserDto.class);
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        User user1 = modelMapper.map(userDto, User.class);
        user1.setId(user.getId());
        return modelMapper.map(userRepository.save(user1), UserDto.class);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<UserDto> getAllUsers() {
        return modelMapper.map(userRepository.findAll(), new TypeToken<List<UserDto>>() {}.getType());
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public List<UserDto> getAllUsersByName(String name) {
        return modelMapper.map(userRepository.findAllByFirstName(name), new TypeToken<List<UserDto>>() {}.getType());
    }
}
