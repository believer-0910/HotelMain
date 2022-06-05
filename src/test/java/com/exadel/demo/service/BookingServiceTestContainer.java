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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class BookingServiceTestContainer {
    @Container
    private static PostgreSQLContainer container = new PostgreSQLContainer("postgres:latest");

    @Mock
    private BookingRepository bookingRepository;

    @Autowired
    private BookingService bookingService;

    @Mock
    private ModelMapper modelMapper;
    private BookingDto bookingDto;
    private BookingEntity bookingEntity;
    private BookingEntity savedBookingEntity;

    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookingService = new BookingService(bookingRepository, modelMapper);
        bookingDto = new BookingDto(new UserDto(), new RoomDto());
        bookingEntity = new BookingEntity(new User(), new Room());
        savedBookingEntity = new BookingEntity(new User(), new Room());

        when(modelMapper.map(bookingDto, BookingEntity.class)).thenReturn(bookingEntity);
        when(modelMapper.map(savedBookingEntity, BookingDto.class)).thenReturn(bookingDto);
        when(bookingRepository.save(bookingEntity)).thenReturn(savedBookingEntity);
    }

    @Test
    void add(){
        BookingDto bookingDtoSaved = bookingService.saveBooking(bookingDto);
        assertNotNull(bookingDtoSaved);
        assertNull(bookingDtoSaved.getUserDto().getEmail());
    }
}
