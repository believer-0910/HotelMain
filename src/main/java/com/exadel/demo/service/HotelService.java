package com.exadel.demo.service;

import com.exadel.demo.dto.HotelDto;
import com.exadel.demo.entity.Hotel;
import com.exadel.demo.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ModelMapper modelMapper;

    @CacheEvict(value = "addHotel", allEntries = true)
    public HotelDto add(HotelDto hotelDto) {
        return modelMapper.map(hotelRepository.save(modelMapper.map(hotelDto, Hotel.class)), HotelDto.class);
    }

    @CacheEvict(value = "getHotel", allEntries = true)
    public HotelDto get(Long id) {
        return modelMapper.map(hotelRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Hotel not found")), HotelDto.class);
    }

    @CacheEvict(value = "updateHotel", allEntries = true)
    public HotelDto update(Long id, HotelDto hotelDto) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Hotel not found"));
        modelMapper.map(hotelDto, hotel);
        return modelMapper.map(hotelRepository.save(hotel), HotelDto.class);
    }

    @CacheEvict(value = "deleteHotel", allEntries = true)
    public void delete(Long id) {
        hotelRepository.deleteById(id);
    }

    @CacheEvict(value = "getAllHotels", allEntries = true)
    public List<HotelDto> getAll() {
        return modelMapper.map(hotelRepository.findAll(), new TypeToken<List<HotelDto>>() {}.getType());
    }

    @CacheEvict(value = "deleteAllHotels", allEntries = true)
    public void deleteAll() {
        hotelRepository.deleteAll();
    }
}
