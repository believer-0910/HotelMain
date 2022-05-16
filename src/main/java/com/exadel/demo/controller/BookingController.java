package com.exadel.demo.controller;

import com.exadel.demo.dto.BookingDto;
import com.exadel.demo.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("getAll")
    public ResponseEntity<List<BookingDto>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<BookingDto> getBookingById(@RequestParam Long id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @PostMapping("add")
    public ResponseEntity<BookingDto> saveBooking(@RequestBody BookingDto bookingDto) {
        return ResponseEntity.ok(bookingService.saveBooking(bookingDto));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<BookingDto> updateBooking(@RequestParam Long id,@RequestBody BookingDto bookingDto) {
        return ResponseEntity.ok(bookingService.update(id, bookingDto));
    }

    @DeleteMapping("delete/{id}")
    public void deleteBooking(@RequestParam Long id) {
        bookingService.deleteBooking(id);
    }

    @DeleteMapping("deleteAll")
    public void deleteAllBookings() {
        bookingService.deleteAllBookings();
    }
}

