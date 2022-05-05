package com.exadel.demo.service.config;

import com.exadel.demo.dto.*;
import com.exadel.demo.entity.Floor;
import com.exadel.demo.entity.Room;
import com.exadel.demo.entity.User;
import com.exadel.demo.repository.HotelRepository;
import com.exadel.demo.repository.RoleRepository;
import com.exadel.demo.repository.TypeRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    public ModelMapperConfig(HotelRepository hotelRepository, RoleRepository roleRepository, TypeRepository typeRepository) {
        this.hotelRepository = hotelRepository;
        this.roleRepository = roleRepository;
        this.typeRepository = typeRepository;
    }

    private final RoleRepository roleRepository;

    private final TypeRepository typeRepository;

    private final HotelRepository hotelRepository;


    @Bean
    ModelMapper modelMapper() {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // User -> UserDTO

        Converter<UserDto, User> UserDtoToUserConverter = new Converter<UserDto, User>() {

            private final ModelMapper modelMapper = new ModelMapper();

            @Override
            public User convert(MappingContext<UserDto, User> context) {
                modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
                User user = modelMapper.map(context.getSource(), User.class);
                if (context.getSource().getRoleDto() != null) {
                    user.setRole(roleRepository.findByName(context.getSource().getRoleDto().getName()));
                }
                return user;
            }
        };
        // Floor -> FloorDTO
        Converter<FloorDto, Floor> FloorDtoFloorConverter = new Converter<FloorDto, Floor>() {
            private final ModelMapper modelMapper = new ModelMapper();

            @Override
            public Floor convert(MappingContext<FloorDto, Floor> context) {
                modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
                Floor floor = modelMapper.map(context.getSource(), Floor.class);
                if (context.getSource().getHotelDto() != null) {
                    floor.setHotel(hotelRepository.findByName(context.getSource().getHotelDto().getName()));
                }
                return floor;
            }
        };

        // Room -> RoomDTO
        Converter<RoomDto, Room> RoomDtoToRoomConverter = new Converter<RoomDto, Room>() {
            private final ModelMapper modelMapper = new ModelMapper();

            @Override
            public Room convert(MappingContext<RoomDto, Room> context) {
                modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
                Room room = modelMapper.map(context.getSource(), Room.class);
                Floor floor = room.getFloor();
                if (context.getSource().getFloorDto() != null) {
                    if (context.getSource().getFloorDto().getHotelDto() != null) {
                        floor.setHotel(hotelRepository.findByName(context.getSource().getFloorDto().getHotelDto().getName()));
                    }
                    floor.setNumber(context.getSource().getFloorDto().getNumber());
                }
                if (context.getSource().getTypeDto() != null) {
                    room.setType(typeRepository.findByType(context.getSource().getTypeDto().getType()));
                }
                room.setFloor(floor);
                return room;
            }
        };

        // RoomDto -> Room
        Converter<Room, RoomDto> RoomToRoomDtoConverter = new Converter<Room, RoomDto>() {
            private final ModelMapper modelMapper = new ModelMapper();

            @Override
            public RoomDto convert(MappingContext<Room, RoomDto> context) {
                modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
                RoomDto roomDto = modelMapper.map(context.getSource(), RoomDto.class);
                if (context.getSource().getFloor() != null) {
                    FloorDto floorDto = new FloorDto();
                    floorDto.setNumber(context.getSource().getFloor().getNumber());
                    if (context.getSource().getFloor().getHotel() != null) {
                        floorDto.setHotelDto(new HotelDto(context.getSource().getFloor().getHotel().getName()));
                    }
                    roomDto.setFloorDto(floorDto);
                }
                if (context.getSource().getType() != null) {
                    roomDto.setTypeDto(new TypeDto(context.getSource().getType().getType()));
                }
                return roomDto;
            }
        };

        // FloorDto -> Floor
        Converter<Floor, FloorDto> FloorToFloorDtoConverter = new Converter<Floor, FloorDto>() {
            private final ModelMapper modelMapper = new ModelMapper();

            @Override
            public FloorDto convert(MappingContext<Floor, FloorDto> context) {
                modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
                FloorDto floorDto = modelMapper.map(context.getSource(), FloorDto.class);
                if (context.getSource().getHotel() != null) {
                    floorDto.setHotelDto(new HotelDto(context.getSource().getHotel().getName()));
                }
                return floorDto;
            }
        };

        // UserDto -> User
        Converter<User, UserDto> UserToUserDtoConverter = new Converter<User, UserDto>() {
            private final ModelMapper modelMapper = new ModelMapper();

            @Override
            public UserDto convert(MappingContext<User, UserDto> context) {
                modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
                UserDto userDto = modelMapper.map(context.getSource(), UserDto.class);
                if (context.getSource().getRole() != null) {
                    userDto.setRoleDto(new RoleDto(context.getSource().getRole().getName()));
                }
                return userDto;
            }
        };

        mapper.addConverter(UserDtoToUserConverter);
        mapper.addConverter(FloorDtoFloorConverter);
        mapper.addConverter(RoomDtoToRoomConverter);
        mapper.addConverter(RoomToRoomDtoConverter);
        mapper.addConverter(FloorToFloorDtoConverter);
        mapper.addConverter(UserToUserDtoConverter);

        return mapper;
    }
}
