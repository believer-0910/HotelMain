package com.exadel.demo.dto;

public class RoomDto {
    private int number;
    private TypeDto typeDto;
    private FloorDto floorDto;

    public RoomDto(int number, TypeDto typeDto, FloorDto floorDto) {
        this.number = number;
        this.typeDto = typeDto;
        this.floorDto = floorDto;
    }

    public RoomDto() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public TypeDto getTypeDto() {
        return typeDto;
    }

    public void setTypeDto(TypeDto typeDto) {
        this.typeDto = typeDto;
    }

    public FloorDto getFloorDto() {
        return floorDto;
    }

    public void setFloorDto(FloorDto floorDto) {
        this.floorDto = floorDto;
    }
}
