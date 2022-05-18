package com.exadel.demo.controller;

import com.exadel.demo.dto.HotelDto;
import com.exadel.demo.service.HotelService;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    private final Logger log;
    private final HotelService hotelService;

    public HotelController(Logger log, HotelService hotelService) {
        this.log = log;
        this.hotelService = hotelService;
    }

    @PostMapping("/add")
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
