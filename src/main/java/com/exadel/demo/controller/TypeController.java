package com.exadel.demo.controller;

import com.exadel.demo.dto.TypeDto;
import com.exadel.demo.service.TypeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type")
public class TypeController {

    private final TypeService typeService;
    private static final Logger log = LogManager.getLogger(TypeController.class);

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TypeDto>> getAll() {
        log.info("get all types");
        return ResponseEntity.ok(typeService.getAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TypeDto> get(@PathVariable("id") Long id) {
        log.info("get type by id: " + id);
        return ResponseEntity.ok(typeService.get(id));
    }

    @PostMapping("/")
    public ResponseEntity<TypeDto> add(@RequestBody TypeDto typeDto) {
        log.info("add type: " + typeDto);
        return ResponseEntity.ok(typeService.add(typeDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TypeDto> update(@PathVariable("id") Long id, @RequestBody TypeDto typeDto) {
        log.info("update type: " + typeDto);
        return ResponseEntity.ok(typeService.update(id, typeDto));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        log.info("delete type by id: " + id);
        typeService.delete(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        log.info("delete all types");
        typeService.deleteAll();
    }
}
