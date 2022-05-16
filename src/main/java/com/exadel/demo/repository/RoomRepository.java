package com.exadel.demo.repository;

import com.exadel.demo.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findAllByFloorId(Long id);

    Room findByNumber(int number);

}
