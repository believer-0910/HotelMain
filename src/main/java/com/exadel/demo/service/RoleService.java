package com.exadel.demo.service;

import com.exadel.demo.dto.RoleDto;
import com.exadel.demo.entity.Role;
import com.exadel.demo.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    public RoleService (RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }


    private final RoleRepository roleRepository;

    private final ModelMapper modelMapper;

    public RoleDto add(RoleDto roleDto) {
        Role role = roleRepository.save(modelMapper.map(roleDto, Role.class));
        return modelMapper.map(role, RoleDto.class);
    }

    public RoleDto get(Long id) {
        return modelMapper.map(roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found")), RoleDto.class);
    }

    public RoleDto update(Long id, RoleDto roleDto) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
        modelMapper.map(roleDto, role);
        return modelMapper.map(roleRepository.save(role), RoleDto.class);
    }

    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    public List<RoleDto> getAll() {
        return modelMapper.map(roleRepository.findAll(), new TypeToken<List<RoleDto>>() {}.getType());
    }

    public void deleteAll() {
        roleRepository.deleteAll();
    }

    public Role getRole(String roleName) {
        return roleRepository.findByName(roleName);
    }

}
