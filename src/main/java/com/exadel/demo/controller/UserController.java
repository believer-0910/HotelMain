package com.exadel.demo.controller;

import com.exadel.demo.dto.UserDto;
import com.exadel.demo.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private static final Logger log = LogManager.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        log.info("add user: " + userDto);
        return ResponseEntity.ok(userService.addUser(userDto));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        log.info("get all users");
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        log.info("get user by id: " + id);
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id,@RequestBody UserDto userDto) {
        log.info("update user: " + userDto);
        return ResponseEntity.ok(userService.updateUser(id, userDto));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        log.info("delete user by id: " + id);
        userService.deleteUser(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllUsers() {
        log.info("delete all users");
        userService.deleteAllUsers();
    }

    @GetMapping("/getAllByName/{name}")
    public ResponseEntity<List<UserDto>> getAllUsersByName(@PathVariable String name) {
        log.info("get all users by name: " + name);
        return ResponseEntity.ok(userService.getAllUsersByName(name));
    }

}
