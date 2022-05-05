package com.exadel.demo.controller;

import com.exadel.demo.dto.TypeDto;
import com.exadel.demo.service.TypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type")
public class TypeController {

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    private final TypeService typeService;

    @GetMapping("/getAll")
    public ResponseEntity<List<TypeDto>> getAll() {
        return ResponseEntity.ok(typeService.getAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TypeDto> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(typeService.get(id));
    }

    @PostMapping("/add")
    public ResponseEntity<TypeDto> add(@RequestBody TypeDto typeDto) {
        return ResponseEntity.ok(typeService.add(typeDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TypeDto> update(@PathVariable("id") Long id, @RequestBody TypeDto typeDto) {
        return ResponseEntity.ok(typeService.update(id, typeDto));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        typeService.delete(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        typeService.deleteAll();
    }
}
