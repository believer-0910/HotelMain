package com.exadel.demo.service;

import com.exadel.demo.dto.RoomDto;
import com.exadel.demo.entity.Room;
import com.exadel.demo.repository.RoomRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    public RoomService (ModelMapper modelMapper, RoomRepository roomRepository) {
        this.modelMapper = modelMapper;
        this.roomRepository = roomRepository;
    }

    private final RoomRepository roomRepository;

    private final ModelMapper modelMapper;

    public RoomDto add(RoomDto roomDto) {
        return modelMapper.map(roomRepository.save(modelMapper.map(roomDto, Room.class)), RoomDto.class);
    }

    public RoomDto update(Long id, RoomDto roomDto) {
        Room room1 = roomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Room with id " + id + " not found"));
        Room room = modelMapper.map(roomDto, Room.class);
        room.setId(room1.getId());
        return modelMapper.map(roomRepository.save(room), RoomDto.class);
    }

    public RoomDto get(Long id) {
        return modelMapper.map(roomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Room with id " + id + " not found")), RoomDto.class);
    }

    public List<RoomDto> getAll() {
        return modelMapper.map(roomRepository.findAll(), new TypeToken<List<RoomDto>>() {}.getType());
    }

    public void delete(Long id) {
        roomRepository.deleteById(id);
    }

    public void deleteAll() {
        roomRepository.deleteAll();
    }

    public List<RoomDto> getByFloorId(Long id) {
        return modelMapper.map(roomRepository.findAllByFloorId(id), new TypeToken<List<RoomDto>>() {}.getType());
    }
}
