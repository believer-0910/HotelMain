
package com.exadel.demo.repository;

import com.exadel.demo.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
    RoomType findByTypeName(String typeName);
}
