package com.exadel.hotel.repository;

import com.exadel.hotel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<List<User>> findAllByFirstName(String firstName);

    User findByEmail(String email);
}
