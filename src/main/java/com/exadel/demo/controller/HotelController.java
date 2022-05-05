package com.exadel.demo.controller;

import com.exadel.demo.dto.HotelDto;
import com.exadel.demo.service.HotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    private final HotelService hotelService;

    @PostMapping("/add")
    public ResponseEntity<HotelDto> add(@RequestBody HotelDto hotelDto) {
        return ResponseEntity.ok(hotelService.add(hotelDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HotelDto> update(@PathVariable("id") Long id, @RequestBody HotelDto hotelDto) {
        return ResponseEntity.ok(hotelService.update(id, hotelDto));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<HotelDto> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(hotelService.get(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<HotelDto>> getAll() {
        return ResponseEntity.ok(hotelService.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        hotelService.delete(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        hotelService.deleteAll();
    }
}
