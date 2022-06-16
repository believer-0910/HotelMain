package com.exadel.demo.service;

import com.exadel.demo.dto.UserDto;
import com.exadel.demo.entity.User;
import com.exadel.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @CacheEvict(value = "addUser", allEntries = true)
    public UserDto addUser(UserDto userDto) {
        return modelMapper.map(userRepository.save(modelMapper.map(userDto, User.class)), UserDto.class);
    }

    @CacheEvict(value = "getUser", allEntries = true)
    public UserDto getUser(Long id) {
        return modelMapper.map(userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found")), UserDto.class);
    }

    @CacheEvict(value = "updateUser", allEntries = true)
    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        User user1 = modelMapper.map(userDto, User.class);
        user1.setId(user.getId());
        return modelMapper.map(userRepository.save(user1), UserDto.class);
    }

    @CacheEvict(value = "deleteUser", allEntries = true)
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @CacheEvict(value = "getAllUsers", allEntries = true)
    public List<UserDto> getAllUsers() {
        return modelMapper.map(userRepository.findAll(), new TypeToken<List<UserDto>>() {}.getType());
    }

    @CacheEvict(value = "deleteAllUsers", allEntries = true)
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    @CacheEvict(value = "getAllUsersByName", allEntries = true)
    public List<UserDto> getAllUsersByName(String name) {
        return modelMapper.map(userRepository.findAllByFirstName(name), new TypeToken<List<UserDto>>() {}.getType());
    }
}
