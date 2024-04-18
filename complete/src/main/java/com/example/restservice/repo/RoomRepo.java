package com.example.restservice.repo;

import com.example.restservice.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface RoomRepo extends JpaRepository<Room, String> {

    List<Room> findByHotelId(Long id);

    Room findByNumber(int number);
}