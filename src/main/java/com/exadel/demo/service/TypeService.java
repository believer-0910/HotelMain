package com.exadel.demo.service;

import com.exadel.demo.dto.TypeDto;
import com.exadel.demo.entity.RoomType;
import com.exadel.demo.repository.RoomTypeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {

    @Autowired
    private RoomTypeRepository typeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @CacheEvict(value = "addType", allEntries = true)
    public TypeDto add(TypeDto typeDto) {
        return modelMapper.map(typeRepository.save(modelMapper.map(typeDto, RoomType.class)), TypeDto.class);
    }

    @CacheEvict(value = "updateType", allEntries = true)
    public TypeDto update(Long id, TypeDto typeDto) {
        RoomType type = typeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Type with id " + id + " not found"));
        type.setType(typeDto.getType());
        return modelMapper.map(typeRepository.save(type), TypeDto.class);
    }

    @CacheEvict(value = "getType", allEntries = true)
    public TypeDto get(Long id) {
        return modelMapper.map(typeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Type with id " + id + " not found")), TypeDto.class);
    }

    @CacheEvict(value = "getAllTypes", allEntries = true)
    public List<TypeDto> getAll() {
        return modelMapper.map(typeRepository.findAll(), new TypeToken<List<TypeDto>>() {}.getType());
    }

    @CacheEvict(value = "deleteType", allEntries = true)
    public void delete(Long id) {
        typeRepository.deleteById(id);
    }

    @CacheEvict(value = "deleteAllTypes", allEntries = true)
    public void deleteAll() {
        typeRepository.deleteAll();
    }

}
