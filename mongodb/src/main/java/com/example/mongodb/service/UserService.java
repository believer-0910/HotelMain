package com.example.mongodb.service;

import com.example.mongodb.document.User;
import com.example.mongodb.dto.UserDto;
import com.example.mongodb.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserDto add(UserDto userDto){
        return modelMapper.map(userRepository.save(modelMapper.map(userDto, User.class)), UserDto.class);
    }

    public UserDto updateUser(String id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        User user1 = modelMapper.map(userDto, User.class);
        user1.setId(user.getId());
        return modelMapper.map(userRepository.save(user1), UserDto.class);
    }

    public UserDto get(String id){
        return modelMapper.map(userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found")), UserDto.class);
    }

    public List<UserDto> getAll(){
        return modelMapper.map(userRepository.findAll(), new TypeToken<List<UserDto>>() {}.getType());
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
