package com.exadel.demo.controller;

import com.exadel.demo.dto.FloorDto;
import com.exadel.demo.service.FloorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/floor")
public class FloorController {

    public FloorController(FloorService floorService) {
        this.floorService = floorService;
    }

    private final FloorService floorService;

    @GetMapping("/getAll")
    public ResponseEntity<List<FloorDto>> getAll() {
        return ResponseEntity.ok(floorService.getAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<FloorDto> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(floorService.get(id));
    }


    @GetMapping("/getByHotel/{id}")
    public ResponseEntity<List<FloorDto>> getByHotel(@PathVariable("id") Long id) {
        return ResponseEntity.ok(floorService.getAllByHotelId(id));
    }

    @PostMapping("/add")
    public ResponseEntity<FloorDto> add(@RequestBody FloorDto floorDto) {
        return ResponseEntity.ok(floorService.add(floorDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FloorDto> update(@PathVariable("id") Long id, @RequestBody FloorDto floorDto) {
        return ResponseEntity.ok(floorService.update(id, floorDto));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        floorService.delete(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        floorService.deleteAll();
    }


}
