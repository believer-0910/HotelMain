package com.exadel.demo.dto;


public class HotelDto {
    private String name;

    public HotelDto(String name) {
        this.name = name;
    }

    public HotelDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
