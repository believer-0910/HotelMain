package com.exadel.demo.service;

import com.exadel.demo.dto.RoomDto;
import com.exadel.demo.entity.Room;
import com.exadel.demo.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ModelMapper modelMapper;

    @CacheEvict(value = "addRoom", allEntries = true)
    public RoomDto add(RoomDto roomDto) {
        return modelMapper.map(roomRepository.save(modelMapper.map(roomDto, Room.class)), RoomDto.class);
    }

    @CacheEvict(value = "updateRoom", allEntries = true)
    public RoomDto update(Long id, RoomDto roomDto) {
        Room room1 = roomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Room with id " + id + " not found"));
        Room room = modelMapper.map(roomDto, Room.class);
        room.setId(room1.getId());
        return modelMapper.map(roomRepository.save(room), RoomDto.class);
    }

    @CacheEvict(value = "getRoom", allEntries = true)
    public RoomDto get(Long id) {
        return modelMapper.map(roomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Room with id " + id + " not found")), RoomDto.class);
    }

    @CacheEvict(value = "getAllRooms", allEntries = true)
    public List<RoomDto> getAll() {
        return modelMapper.map(roomRepository.findAll(), new TypeToken<List<RoomDto>>() {}.getType());
    }

    @CacheEvict(value = "deleteRoom", allEntries = true)
    public void delete(Long id) {
        roomRepository.deleteById(id);
    }

    @CacheEvict(value = "deleteAllRooms", allEntries = true)
    public void deleteAll() {
        roomRepository.deleteAll();
    }

    @CacheEvict(value = "getRoomFloorId", allEntries = true)
    public List<RoomDto> getByFloorId(Long id) {
        return modelMapper.map(roomRepository.findAllByFloorId(id), new TypeToken<List<RoomDto>>() {}.getType());
    }
}
