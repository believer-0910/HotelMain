package com.exadel.demo.entity;

import javax.persistence.*;
@Entity
public class Floor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int number;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public Floor(Long id, int number, Hotel hotel) {
        this.id = id;
        this.number = number;
        this.hotel = hotel;
    }

    public Floor (){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

}
