package com.example.restservice.repo;

import com.example.restservice.domain.Hotel;
import com.example.restservice.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepo extends JpaRepository<Hotel, Long> {
    Hotel findByName(String hotelName);

}
