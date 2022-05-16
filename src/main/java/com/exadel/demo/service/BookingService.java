package com.exadel.demo.service;

import com.exadel.demo.dto.BookingDto;
import com.exadel.demo.entity.BookingEntity;
import com.exadel.demo.repository.BookingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    private final ModelMapper modelMapper;

    public BookingService(BookingRepository bookingRepository, ModelMapper modelMapper) {
        this.bookingRepository = bookingRepository;
        this.modelMapper = modelMapper;
    }

    public List<BookingDto> getAllBookings() {
        return bookingRepository.findAll().stream().map(booking -> modelMapper.map(booking, BookingDto.class)).collect(java.util.stream.Collectors.toList());
    }

    public BookingDto getBookingById(Long id) {
        return modelMapper.map(bookingRepository.findById(id).orElse(null), BookingDto.class);
    }

    public BookingDto saveBooking(BookingDto bookingDto) {
        return modelMapper.map(bookingRepository.save(modelMapper.map(bookingDto, BookingEntity.class)), BookingDto.class);
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    public BookingDto update(Long id, BookingDto bookingDto) {
        BookingDto bookingById = getBookingById(id);
        BookingEntity bookingEntity = modelMapper.map(bookingDto, BookingEntity.class);
        bookingEntity.setId(id);
        return modelMapper.map(bookingRepository.save(bookingEntity), BookingDto.class);
    }

    public void deleteAllBookings() {
        bookingRepository.deleteAll();
    }
}
