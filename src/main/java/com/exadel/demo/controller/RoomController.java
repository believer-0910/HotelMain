package com.exadel.demo.controller;

import com.exadel.demo.dto.RoomDto;
import com.exadel.demo.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    private final RoomService roomService;

    @GetMapping("/getAll")
    public ResponseEntity<List<RoomDto>> getAll() {
        return ResponseEntity.ok(roomService.getAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<RoomDto> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(roomService.get(id));
    }

    @GetMapping("/getByFloorId/{floorId}")
    public ResponseEntity<List<RoomDto>> getRoomsByFloorId(@PathVariable("floorId") Long floorId) {
        return ResponseEntity.ok(roomService.getByFloorId(floorId));
    }

    @PostMapping("/add")
    public ResponseEntity<RoomDto> add(@RequestBody RoomDto roomDto) {
        return ResponseEntity.ok(roomService.add(roomDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RoomDto> update(@PathVariable("id") Long id, @RequestBody RoomDto roomDto) {
        return ResponseEntity.ok(roomService.update(id, roomDto));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        roomService.delete(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        roomService.deleteAll();
    }
}
