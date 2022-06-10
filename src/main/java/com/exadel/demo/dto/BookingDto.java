package com.exadel.demo.dto;

public class BookingDto {

    private Long id;

    private UserDto userDto;

    private RoomDto roomDto;

    public BookingDto(){
    }

    public BookingDto(Long id, UserDto userDto, RoomDto roomDto) {
        this.id = id;
        this.userDto = userDto;
        this.roomDto = roomDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public RoomDto getRoomDto() {
        return roomDto;
    }

    public void setRoomDto(RoomDto roomDto) {
        this.roomDto = roomDto;
    }
}
