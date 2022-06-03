package com.exadel.demo.service;

import com.exadel.demo.dto.BookingDto;
import com.exadel.demo.dto.RoomDto;
import com.exadel.demo.dto.UserDto;
import com.exadel.demo.entity.BookingEntity;
import com.exadel.demo.entity.Room;
import com.exadel.demo.entity.User;
import com.exadel.demo.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Testcontainers
public class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private ModelMapper modelMapper;

    private BookingEntity bookingEntity;

    private BookingEntity savedBookingEntity;

    private BookingDto bookingDto;

    private BookingService bookingService;

    @Container
    private static PostgreSQLContainer container = new PostgreSQLContainer("postgres:latest");

    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        bookingService = new BookingService(bookingRepository, modelMapper);
        bookingEntity = new BookingEntity(new User(), new Room());
        savedBookingEntity = new BookingEntity(new User(), new Room());
        bookingDto = new BookingDto(new UserDto(), new RoomDto());

        when(modelMapper.map(bookingDto, BookingEntity.class)).thenReturn(bookingEntity);
        when(modelMapper.map(savedBookingEntity, BookingDto.class)).thenReturn(bookingDto);
        when(bookingRepository.save(bookingEntity)).thenReturn(savedBookingEntity);
        when(bookingRepository.findById(1L)).thenReturn(Optional.of(bookingEntity));
    }

    @Test
    void add(){
        BookingDto bookingDtoSaved = bookingService.saveBooking(bookingDto);
        assertNotNull(bookingDtoSaved);
        assertNull(bookingDtoSaved.getUserDto().getEmail());
    }

    @Test
    void getAll(){
        List<BookingDto> all = bookingService.getAllBookings();
        when(bookingRepository.findAll()).thenReturn(new ArrayList<>());
        assertArrayEquals(new ArrayList<>().toArray(), all.toArray());
    }

    @Test
    void get(){
        when(modelMapper.map(bookingEntity, BookingDto.class)).thenReturn(bookingDto);
        BookingDto bookingDto = bookingService.getBookingById(1L);
        assertNotNull(bookingDto);
        assertNull(bookingDto.getUserDto().getEmail());
    }

    @Test
    void update(){
        BookingDto updated = bookingService.update(1L, bookingDto);
        assertNotNull(updated);
        assertNull(updated.getUserDto().getEmail());
    }

    @Test
    void delete(){
        bookingService.deleteBooking(1L);
        verify(bookingRepository).deleteById(1L);
    }

    @Test
    void deleteAll(){
        bookingService.deleteAllBookings();
        verify(bookingRepository).deleteAll();
    }
}
