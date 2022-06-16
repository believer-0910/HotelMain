package com.exadel.demo.service;

import com.exadel.demo.dto.RoleDto;
import com.exadel.demo.entity.Role;
import com.exadel.demo.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public RoleService(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @CacheEvict(value = "addRole", allEntries = true)
    public RoleDto add(RoleDto roleDto) {
        Role role = roleRepository.save(modelMapper.map(roleDto, Role.class));
        return modelMapper.map(role, RoleDto.class);
    }

    @CacheEvict(value = "getRole", allEntries = true)
    public RoleDto get(Long id) {
        return modelMapper.map(roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found")), RoleDto.class);
    }

    @CacheEvict(value = "updateRole", allEntries = true)
    public RoleDto update(Long id, RoleDto roleDto) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
        modelMapper.map(roleDto, role);
        return modelMapper.map(roleRepository.save(role), RoleDto.class);
    }

    @CacheEvict(value = "deleteRole", allEntries = true)
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    @CacheEvict(value = "getAllRoles", allEntries = true)
    public List<RoleDto> getAll() {
        return modelMapper.map(roleRepository.findAll(), new TypeToken<List<RoleDto>>() {}.getType());
    }

    @CacheEvict(value = "deleteAllRoles", allEntries = true)
    public void deleteAll() {
        roleRepository.deleteAll();
    }

}
