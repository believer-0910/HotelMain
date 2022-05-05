package com.exadel.demo.controller;

import com.exadel.demo.dto.TypeDto;
import com.exadel.demo.service.TypeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
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

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TypeController.class})
public class TypeControllerTest {

    @Autowired
    private TypeController typeController;

    @MockBean
    private TypeService typeService;

    private TypeDto typeDto;

    @BeforeEach
    void setUp() {
        typeDto = new TypeDto("Type");

        when(this.typeService.get(123L)).thenReturn(typeDto);

        when(this.typeService.getAll()).thenReturn(new ArrayList<>());

        when(this.typeService.add(any())).thenReturn(typeDto);

        when(this.typeService.update(any(), any())).thenReturn(typeDto);

        doNothing().when(this.typeService).delete(123L);

        doNothing().when(this.typeService).deleteAll();
    }


    @Test
    void testGet() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/type/get/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.typeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"type\":\"Type\"}"));
    }

    @Test
    void testGetAll() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/type/getAll");
        MockMvcBuilders.standaloneSetup(this.typeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testAdd() throws Exception {

        String content = (new ObjectMapper()).writeValueAsString(typeDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/type/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.typeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"type\":\"Type\"}"));
    }

    @Test
    void testUpdate() throws Exception {
        String content = (new ObjectMapper()).writeValueAsString(typeDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/type/update/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.typeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"type\":\"Type\"}"));
    }

    @Test
    void testDelete() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/type/delete/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.typeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteAll() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/type/deleteAll");
        MockMvcBuilders.standaloneSetup(this.typeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
