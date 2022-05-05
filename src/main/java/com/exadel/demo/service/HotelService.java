package com.exadel.demo.service;

import com.exadel.demo.dto.HotelDto;
import com.exadel.demo.entity.Hotel;
import com.exadel.demo.repository.HotelRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    public HotelService(HotelRepository hotelRepository, ModelMapper modelMapper) {
        this.hotelRepository = hotelRepository;
        this.modelMapper = modelMapper;
    }

    private final HotelRepository hotelRepository;

    private final ModelMapper modelMapper;

    public HotelDto add(HotelDto hotelDto) {
        return modelMapper.map(hotelRepository.save(modelMapper.map(hotelDto, Hotel.class)), HotelDto.class);
    }

    public HotelDto get(Long id) {
        return modelMapper.map(hotelRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Hotel not found")), HotelDto.class);
    }

    public HotelDto update(Long id, HotelDto hotelDto) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Hotel not found"));
        modelMapper.map(hotelDto, hotel);
        return modelMapper.map(hotelRepository.save(hotel), HotelDto.class);
    }

    public void delete(Long id) {
        hotelRepository.deleteById(id);
    }

    public List<HotelDto> getAll() {
        return modelMapper.map(hotelRepository.findAll(), new TypeToken<List<HotelDto>>() {}.getType());
    }

    public void deleteAll() {
        hotelRepository.deleteAll();
    }
}
