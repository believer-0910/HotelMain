package com.exadel.demo.controller;

import com.exadel.demo.dto.FloorDto;
import com.exadel.demo.service.FloorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/floor")
public class FloorController {

    private static final Logger log = LogManager.getLogger(FloorController.class);
    private final FloorService floorService;

    public FloorController(FloorService floorService) {
        this.floorService = floorService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<FloorDto>> getAll() {
        log.info("get all floors");
        return ResponseEntity.ok(floorService.getAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<FloorDto> get(@PathVariable("id") Long id) {
        log.info("get floor by id: " + id);
        return ResponseEntity.ok(floorService.get(id));
    }


    @GetMapping("/getByHotel/{id}")
    public ResponseEntity<List<FloorDto>> getByHotel(@PathVariable("id") Long id) {
        log.info("get floors by hotel id: " + id);
        return ResponseEntity.ok(floorService.getAllByHotelId(id));
    }

    @PostMapping("/")
    public ResponseEntity<FloorDto> add(@RequestBody FloorDto floorDto) {
        log.info("add floor: " + floorDto);
        return ResponseEntity.ok(floorService.add(floorDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FloorDto> update(@PathVariable("id") Long id, @RequestBody FloorDto floorDto) {
        log.info("update floor: " + floorDto);
        return ResponseEntity.ok(floorService.update(id, floorDto));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        log.info("delete floor by id: " + id);
        floorService.delete(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        log.info("delete all floors");
        floorService.deleteAll();
    }

}
