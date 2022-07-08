package com.exadel.demo.controller;

import com.exadel.demo.dto.RoomDto;
import com.exadel.demo.service.RoomService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;
    private static final Logger log = LogManager.getLogger(BookingController.class);

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<RoomDto>> getAll() {
        log.info("get all rooms");
        return ResponseEntity.ok(roomService.getAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<RoomDto> get(@PathVariable("id") Long id) {
        log.info("get room by id: " + id);
        return ResponseEntity.ok(roomService.get(id));
    }

    @GetMapping("/getByFloorId/{floorId}")
    public ResponseEntity<List<RoomDto>> getRoomsByFloorId(@PathVariable("floorId") Long floorId) {
        log.info("get rooms by floor id: " + floorId);
        return ResponseEntity.ok(roomService.getByFloorId(floorId));
    }

    @PostMapping("/")
    public ResponseEntity<RoomDto> add(@RequestBody RoomDto roomDto) {
        log.info("add room: " + roomDto);
        return ResponseEntity.ok(roomService.add(roomDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RoomDto> update(@PathVariable("id") Long id, @RequestBody RoomDto roomDto) {
        return ResponseEntity.ok(roomService.update(id, roomDto));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        log.info("delete room by id: " + id);
        roomService.delete(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        log.info("delete all rooms");
        roomService.deleteAll();
    }
}
