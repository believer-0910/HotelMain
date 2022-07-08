package com.exadel.demo.controller;

import com.exadel.demo.dto.RoleDto;
import com.exadel.demo.service.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;
    private static final Logger log = LogManager.getLogger(RoleController.class);

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/")
    public ResponseEntity<RoleDto> add(@RequestBody RoleDto roleDto) {
        log.info("add role: " + roleDto);
        return ResponseEntity.ok(roleService.add(roleDto));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<RoleDto> get(@PathVariable("id") Long id) {
        log.info("get role by id: " + id);
        return ResponseEntity.ok(roleService.get(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<RoleDto>> getAll() {
        log.info("get all roles");
        return ResponseEntity.ok(roleService.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        log.info("delete role by id: " + id);
        roleService.delete(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        log.info("delete all roles");
        roleService.deleteAll();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RoleDto> update(@PathVariable("id") Long id, @RequestBody RoleDto roleDto) {
        log.info("update role: " + roleDto);
        return ResponseEntity.ok(roleService.update(id, roleDto));
    }

}
