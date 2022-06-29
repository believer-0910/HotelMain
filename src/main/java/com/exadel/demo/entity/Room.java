package com.exadel.demo.entity;

import javax.persistence.*;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;

    private int number;

    @ManyToOne
    private RoomType roomType;

    @ManyToOne(cascade = CascadeType.ALL)
    private Floor floor;

    public Room(Long id, int number, RoomType roomType, Floor floor) {
        this.id = id;
        this.number = number;
        this.roomType = roomType;
        this.floor = floor;
    }

    public Room() {
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

    public RoomType getType() {
        return roomType;
    }

    public void setType(RoomType type) {
        this.roomType = type;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }
}
