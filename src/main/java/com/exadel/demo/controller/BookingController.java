package com.exadel.demo.controller;

import com.exadel.demo.dto.BookingDto;
import com.exadel.demo.service.BookingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;
    private static final Logger log = LogManager.getLogger(BookingController.class);

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("getAll")
    public ResponseEntity<List<BookingDto>> getAllBookings() {
        log.info("get all bookings");
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<BookingDto> getBookingById(@RequestParam Long id) {
        log.info("get booking by id: " + id);
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @PostMapping("add")
    public ResponseEntity<BookingDto> saveBooking(@RequestBody BookingDto bookingDto) {
        log.info("add booking: " + bookingDto);
        return ResponseEntity.ok(bookingService.saveBooking(bookingDto));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<BookingDto> updateBooking(@RequestParam Long id,@RequestBody BookingDto bookingDto) {
        log.info("update booking: " + bookingDto);
        return ResponseEntity.ok(bookingService.update(id, bookingDto));
    }

    @DeleteMapping("delete/{id}")
    public void deleteBooking(@RequestParam Long id) {
        log.info("delete booking by id: " + id);
        bookingService.deleteBooking(id);
    }

    @DeleteMapping("deleteAll")
    public void deleteAllBookings() {
        log.info("delete all bookings");
        bookingService.deleteAllBookings();
    }
}

