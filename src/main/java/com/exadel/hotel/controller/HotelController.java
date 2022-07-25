package com.exadel.hotel.controller;

import com.exadel.hotel.dto.HotelDto;
import com.exadel.hotel.service.HotelService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    private static final Logger log = LogManager.getLogger(HotelController.class);
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping("/")
    public ResponseEntity<HotelDto> add(@RequestBody HotelDto hotelDto) {
        log.info("add hotel: " + hotelDto);
        return ResponseEntity.ok(hotelService.add(hotelDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HotelDto> update(@PathVariable("id") Long id, @RequestBody HotelDto hotelDto) {
        log.info("update hotel: " + hotelDto);
        return ResponseEntity.ok(hotelService.update(id, hotelDto));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<HotelDto> get(@PathVariable("id") Long id) {
        log.info("get hotel by id: " + id);
        return ResponseEntity.ok(hotelService.get(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<HotelDto>> getAll() {
        log.info("get all hotels");
        return ResponseEntity.ok(hotelService.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        log.info("delete hotel by id: " + id);
        hotelService.delete(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        log.info("delete all hotels");
        hotelService.deleteAll();
    }
}
