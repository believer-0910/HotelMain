package com.exadel.demo.controller;

import com.exadel.demo.dto.HotelDto;
import com.exadel.demo.service.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    Logger log = LoggerFactory.getLogger(HotelController.class);

    private final HotelService hotelService;

    @PostMapping("/add")
    public ResponseEntity<HotelDto> add(@RequestBody HotelDto hotelDto) {
        log.info("add, HotelController");
        return ResponseEntity.ok(hotelService.add(hotelDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HotelDto> update(@PathVariable("id") Long id, @RequestBody HotelDto hotelDto) {
        log.info("update, HotelController");
        return ResponseEntity.ok(hotelService.update(id, hotelDto));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<HotelDto> get(@PathVariable("id") Long id) {
        log.info("get, HotelController");
        return ResponseEntity.ok(hotelService.get(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<HotelDto>> getAll() {
        log.info("getAll, HotelController");
        return ResponseEntity.ok(hotelService.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        log.info("delete, HotelController");
        hotelService.delete(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        log.info("deleteAll, HotelController");
        hotelService.deleteAll();
    }
}
