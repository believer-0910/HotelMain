package com.exadel.demo.controller;

import com.exadel.demo.dto.RoleDto;
import com.exadel.demo.dto.UserDto;
import com.exadel.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {

    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    @MockBean
    private Logger logger;

    @Test
    void testGetAllUsers() throws Exception {
        doNothing().when(this.logger).info((String) any());
        when(this.userService.getAllUsers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/getAll");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetAllUsers2() throws Exception {
        doNothing().when(this.logger).info((String) any());
        when(this.userService.getAllUsers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/user/getAll");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetUserById() throws Exception {
        doNothing().when(this.logger).info((String) any());
        when(this.userService.getUser((Long) any())).thenReturn(new UserDto());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/user/get/*");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"firstName\":null,\"lastName\":null,\"email\":null,\"roleDto\":null}"));
    }

    @Test
    void testGetUserById2() throws Exception {
        doNothing().when(this.logger).info((String) any());
        when(this.userService.getUser((Long) any())).thenReturn(new UserDto());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/user/get/*");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"firstName\":null,\"lastName\":null,\"email\":null,\"roleDto\":null}"));
    }

    @Test
    void testSaveUser() throws Exception {
        doNothing().when(this.logger).info((String) any());
        when(this.userService.addUser((UserDto) any())).thenReturn(new UserDto());

        UserDto userDto = new UserDto();
        userDto.setFirstName("");
        userDto.setLastName("");
        userDto.setEmail("");
        userDto.setRoleDto(new RoleDto());
        String content = (new ObjectMapper()).writeValueAsString(userDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"firstName\":null,\"lastName\":null,\"email\":null,\"roleDto\":null}"));
    }

    @Test
    void testDeleteUser() throws Exception {
        doNothing().when(this.logger).info((String) any());
        doNothing().when(this.userService).deleteUser((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/user/delete/*");
        MockHttpServletRequestBuilder requestBuilder = deleteResult.param("id", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteUser2() throws Exception {
        doNothing().when(this.logger).info((String) any());
        doNothing().when(this.userService).deleteUser((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/user/delete/*");
        MockHttpServletRequestBuilder requestBuilder = deleteResult.param("id", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteAllUsers() throws Exception {
        doNothing().when(this.logger).info((String) any());
        doNothing().when(this.userService).deleteAllUsers();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/user/deleteAll");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteAllUsers2() throws Exception {
        doNothing().when(this.logger).info((String) any());
        doNothing().when(this.userService).deleteAllUsers();
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/user/deleteAll");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testUpdateUser() throws Exception {
        doNothing().when(this.logger).info((String) any());
        when(this.userService.updateUser((Long) any(), (UserDto) any())).thenReturn(new UserDto());

        UserDto userDto = new UserDto();
        userDto.setFirstName("");
        userDto.setLastName("");
        userDto.setEmail("");
        String content = (new ObjectMapper()).writeValueAsString(userDto);
        MockHttpServletRequestBuilder putResult = MockMvcRequestBuilders.put("/user/update/*");
        MockHttpServletRequestBuilder requestBuilder = putResult.param("id", String.valueOf(1L))
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"firstName\":null,\"lastName\":null,\"email\":null,\"roleDto\":null}"));
    }
}
