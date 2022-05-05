package com.exadel.demo.controller;

import com.exadel.demo.dto.RoleDto;
import com.exadel.demo.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    private final RoleService roleService;

    @PostMapping("/add")
    public ResponseEntity<RoleDto> add(@RequestBody RoleDto roleDto) {
        return ResponseEntity.ok(roleService.add(roleDto));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<RoleDto> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(roleService.get(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<RoleDto>> getAll() {
        return ResponseEntity.ok(roleService.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        roleService.delete(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        roleService.deleteAll();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RoleDto> update(@PathVariable("id") Long id, @RequestBody RoleDto roleDto) {
        return ResponseEntity.ok(roleService.update(id, roleDto));
    }

}
