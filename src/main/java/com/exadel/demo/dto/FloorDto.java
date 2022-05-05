package com.exadel.demo.dto;

public class FloorDto {
    private int number;

    private HotelDto hotelDto;

    public FloorDto(int number, HotelDto hotelDto) {
        this.number = number;
        this.hotelDto = hotelDto;
    }

    public FloorDto() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public HotelDto getHotelDto() {
        return hotelDto;
    }

    public void setHotelDto(HotelDto hotelDto) {
        this.hotelDto = hotelDto;
    }
}
