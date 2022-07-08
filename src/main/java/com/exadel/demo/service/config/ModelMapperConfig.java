package com.exadel.demo.service.config;

import com.exadel.demo.dto.*;
import com.exadel.demo.entity.*;
import com.exadel.demo.repository.*;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class ModelMapperConfig {

    private final RoleRepository roleRepository;

    private final RoomTypeRepository typeRepository;

    private final HotelRepository hotelRepository;

    private final FloorRepository floorRepository;

    public ModelMapperConfig(RoleRepository roleRepository, RoomTypeRepository typeRepository, HotelRepository hotelRepository, FloorRepository floorRepository) {
        this.roleRepository = roleRepository;
        this.typeRepository = typeRepository;
        this.hotelRepository = hotelRepository;
        this.floorRepository = floorRepository;
    }

    @Bean
    ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);


        Converter<UserDto, User> userDtoToUserConverter = new Converter<UserDto, User>() {

            private final ModelMapper modelMapper = new ModelMapper();

            @Override
            public User convert(MappingContext<UserDto, User> context) {
                modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
                User user = modelMapper.map(context.getSource(), User.class);
                if (context.getSource().getRoleDto() != null) {
                    Optional<Role> roleRepositoryByName = roleRepository.findByName(context.getSource().getRoleDto().getName());
                    roleRepositoryByName.ifPresent(user::setRole);
                }
                return user;
            }
        };

        Converter<FloorDto, Floor> floorDtoFloorConverter = new Converter<FloorDto, Floor>() {
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

        Converter<RoomDto, Room> roomDtoToRoomConverter = new Converter<RoomDto, Room>() {
            private final ModelMapper modelMapper = new ModelMapper();

            @Override
            public Room convert(MappingContext<RoomDto, Room> context) {
                modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
                Room room = modelMapper.map(context.getSource(), Room.class);
                Floor floor = modelMapper.map(context.getSource().getFloorDto(), Floor.class);
                if (context.getSource().getFloorDto() != null) {
                    if (context.getSource().getFloorDto().getHotelDto() != null) {
                        floor.setHotel(hotelRepository.findByName(context.getSource().getFloorDto().getHotelDto().getName()));
                    }
                    floor.setNumber(context.getSource().getFloorDto().getNumber());
                }
                if (context.getSource().getTypeDto() != null) {
                    room.setType(typeRepository.findByTypeName(context.getSource().getTypeDto().getType()));
                }
                room.setFloor(floor);
                return room;
            }
        };

        Converter<Room, RoomDto> roomToRoomDtoConverter = new Converter<Room, RoomDto>() {
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

        Converter<Floor, FloorDto> floorToFloorDtoConverter = new Converter<Floor, FloorDto>() {
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

        Converter<User, UserDto> userToUserDtoConverter = new Converter<User, UserDto>() {
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

        Converter<BookingDto, BookingEntity> bookingDtoToBookingConverter = new Converter<BookingDto, BookingEntity>() {

            private final ModelMapper modelMapper = new ModelMapper();

            @Override
            public BookingEntity convert(MappingContext<BookingDto, BookingEntity> context) {
                modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
                BookingEntity booking = modelMapper.map(context.getSource(), BookingEntity.class);
                if (context.getSource().getUserDto() != null) {
                    User user = new User();
                    user.setFirstName(context.getSource().getUserDto().getFirstName());
                    user.setLastName(context.getSource().getUserDto().getLastName());
                    user.setEmail(context.getSource().getUserDto().getEmail());
                    if (context.getSource().getUserDto().getRoleDto() != null) {
                        Optional<Role> roleByName = roleRepository.findByName(context.getSource().getUserDto().getRoleDto().getName());
                        roleByName.ifPresent(user::setRole);
                    }
                    booking.setUser(user);
                }
                if (context.getSource().getRoomDto() != null) {
                    Room room = new Room();
                    room.setNumber(context.getSource().getRoomDto().getNumber());
                    if (context.getSource().getRoomDto().getFloorDto() != null) {
                        Floor floor = new Floor();
                        floor.setNumber(context.getSource().getRoomDto().getFloorDto().getNumber());
                        if (context.getSource().getRoomDto().getFloorDto().getHotelDto() != null) {
                            floor.setHotel(hotelRepository.findByName(context.getSource().getRoomDto().getFloorDto().getHotelDto().getName()));
                        }
                        room.setFloor(floor);
                    }
                    if (context.getSource().getRoomDto().getTypeDto() != null) {
                        room.setType(typeRepository.findByTypeName(context.getSource().getRoomDto().getTypeDto().getType()));
                    }
                    booking.setRoom(room);
                }
                return booking;
            }
        };

        Converter<BookingEntity, BookingDto> bookingToBookingDtoConverter = new Converter<BookingEntity, BookingDto>() {
            private final ModelMapper modelMapper = new ModelMapper();

            @Override
            public BookingDto convert(MappingContext<BookingEntity, BookingDto> context) {
                modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
                BookingDto bookingDto = modelMapper.map(context.getSource(), BookingDto.class);
                if (context.getSource().getUser() != null) {
                    UserDto userDto = new UserDto();
                    userDto.setFirstName(context.getSource().getUser().getFirstName());
                    userDto.setLastName(context.getSource().getUser().getLastName());
                    userDto.setEmail(context.getSource().getUser().getEmail());
                    if (context.getSource().getUser().getRole() != null) {
                        userDto.setRoleDto(new RoleDto(context.getSource().getUser().getRole().getName()));
                    }
                    bookingDto.setUserDto(userDto);
                }
                if (context.getSource().getRoom() != null) {
                    RoomDto roomDto = new RoomDto();
                    roomDto.setNumber(context.getSource().getRoom().getNumber());
                    if (context.getSource().getRoom().getFloor() != null) {
                        FloorDto floorDto = new FloorDto();
                        floorDto.setNumber(context.getSource().getRoom().getFloor().getNumber());
                        if (context.getSource().getRoom().getFloor().getHotel() != null) {
                            floorDto.setHotelDto(new HotelDto(context.getSource().getRoom().getFloor().getHotel().getName()));
                        }
                        roomDto.setFloorDto(floorDto);
                    }
                    if (context.getSource().getRoom().getType() != null) {
                        roomDto.setTypeDto(new TypeDto(context.getSource().getRoom().getType().getType()));
                    }
                    bookingDto.setRoomDto(roomDto);
                }
                return bookingDto;
            }
        };

        modelMapper.addConverter(userDtoToUserConverter);
        modelMapper.addConverter(floorDtoFloorConverter);
        modelMapper.addConverter(roomDtoToRoomConverter);
        modelMapper.addConverter(roomToRoomDtoConverter);
        modelMapper.addConverter(floorToFloorDtoConverter);
        modelMapper.addConverter(userToUserDtoConverter);
        modelMapper.addConverter(bookingDtoToBookingConverter);
        modelMapper.addConverter(bookingToBookingDtoConverter);

        return modelMapper;
    }
}
