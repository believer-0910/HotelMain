package com.exadel.demo.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.exadel.demo.dto.BookingDto;
import com.exadel.demo.dto.RoomDto;
import com.exadel.demo.dto.UserDto;
import com.exadel.demo.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

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

@ContextConfiguration(classes = {BookingController.class})
@ExtendWith(SpringExtension.class)
class BookingControllerTest {
    @Autowired
    private BookingController bookingController;

    @MockBean
    private BookingService bookingService;

    @MockBean
    private Logger logger;

    @Test
    void testGetAllBookings() throws Exception {
        doNothing().when(this.logger).info((String) any());
        when(this.bookingService.getAllBookings()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/booking/getAll");
        MockMvcBuilders.standaloneSetup(this.bookingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetAllBookings2() throws Exception {
        doNothing().when(this.logger).info((String) any());
        when(this.bookingService.getAllBookings()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/booking/getAll");
        MockMvcBuilders.standaloneSetup(this.bookingController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetBookingById() throws Exception {
        doNothing().when(this.logger).info((String) any());
        when(this.bookingService.getBookingById((Long) any())).thenReturn(new BookingDto());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/booking/getById/*");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(this.bookingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"userDto\":null,\"roomDto\":null}"));
    }

    @Test
    void testGetBookingById2() throws Exception {
        doNothing().when(this.logger).info((String) any());
        when(this.bookingService.getBookingById((Long) any())).thenReturn(new BookingDto());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/booking/getById/*");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(this.bookingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"userDto\":null,\"roomDto\":null}"));
    }

    @Test
    void testSaveBooking() throws Exception {
        doNothing().when(this.logger).info((String) any());
        when(this.bookingService.saveBooking((BookingDto) any())).thenReturn(new BookingDto());

        BookingDto bookingDto = new BookingDto();
        bookingDto.setRoomDto(new RoomDto());
        bookingDto.setUserDto(new UserDto());
        String content = (new ObjectMapper()).writeValueAsString(bookingDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/booking/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.bookingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"userDto\":null,\"roomDto\":null}"));
    }

    @Test
    void testDeleteBooking() throws Exception {
        doNothing().when(this.logger).info((String) any());
        doNothing().when(this.bookingService).deleteBooking((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/booking/delete/*");
        MockHttpServletRequestBuilder requestBuilder = deleteResult.param("id", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(this.bookingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteBooking2() throws Exception {
        doNothing().when(this.logger).info((String) any());
        doNothing().when(this.bookingService).deleteBooking((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/booking/delete/*");
        MockHttpServletRequestBuilder requestBuilder = deleteResult.param("id", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(this.bookingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteAllBookings() throws Exception {
        doNothing().when(this.logger).info((String) any());
        doNothing().when(this.bookingService).deleteAllBookings();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/booking/deleteAll");
        MockMvcBuilders.standaloneSetup(this.bookingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteAllBookings2() throws Exception {
        doNothing().when(this.logger).info((String) any());
        doNothing().when(this.bookingService).deleteAllBookings();
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/booking/deleteAll");
        MockMvcBuilders.standaloneSetup(this.bookingController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testUpdateBooking() throws Exception {
        doNothing().when(this.logger).info((String) any());
        when(this.bookingService.update((Long) any(), (BookingDto) any())).thenReturn(new BookingDto());

        BookingDto bookingDto = new BookingDto();
        bookingDto.setRoomDto(new RoomDto());
        bookingDto.setUserDto(new UserDto());
        String content = (new ObjectMapper()).writeValueAsString(bookingDto);
        MockHttpServletRequestBuilder putResult = MockMvcRequestBuilders.put("/booking/update/*");
        MockHttpServletRequestBuilder requestBuilder = putResult.param("id", String.valueOf(1L))
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.bookingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"userDto\":null,\"roomDto\":null}"));
    }
}

