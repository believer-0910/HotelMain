package com.exadel.demo.repository;

import com.exadel.demo.entity.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Iterator;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Long> {
    Iterator<Floor> findAllByHotelId(Long hotelId);

}
