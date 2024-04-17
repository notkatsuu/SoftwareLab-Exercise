package com.example.restservice.api;

import com.example.restservice.domain.Hotel;
import com.example.restservice.domain.Room;
import com.example.restservice.repo.HotelRepo;
import com.example.restservice.repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private HotelRepo hotelRepo;

    @Autowired
    private RoomRepo roomRepo;

    @GetMapping("/hotels")
    public List<Hotel> getAllHotels() {
        return hotelRepo.findAll();
    }

    @GetMapping("/hotels/{id}/rooms")
    public List<Room> getHotelRooms(@PathVariable Long id) {
        Hotel hotel = hotelRepo.findById(id).orElseThrow(() -> new RuntimeException("Hotel not found"));
        return hotel.getAllRooms();
    }



    @PostMapping("/hotels/create")
    public Hotel createHotel(@RequestBody Hotel hotel) {
        if (hotel.getHotelName() == null || hotel.getHotelName().isEmpty()) {
            throw new IllegalArgumentException("Hotel name cannot be null or empty");
        }
        Hotel savedHotel = hotelRepo.save(hotel);
        for (Room room : hotel.getAllRooms()) {
            room.setHotel(savedHotel);
            roomRepo.save(room);
        }
        return savedHotel;
    }

    @PutMapping("/hotels/book")
    public List<Room> bookRooms(@RequestParam String hotelName, @RequestParam String booker, @RequestParam int numberOfRooms) {
        Hotel hotel = hotelRepo.findByName(hotelName);
        if (hotel == null) {
            throw new RuntimeException("Hotel not found for this name: " + hotelName);
        }
        List<Room> rooms = hotel.getAllRooms();
        List<Room> bookedRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (!room.isBooked()) {
                room.setBooker(booker);
                bookedRooms.add(roomRepo.save(room));
                if (bookedRooms.size() == numberOfRooms) {
                    break;
                }
            }
        }
        if (bookedRooms.size() < numberOfRooms) {
            throw new RuntimeException("Not enough available rooms in this hotel");
        }
        return bookedRooms;
    }

    @DeleteMapping("/hotels/deleteAll")
    public void deleteAllHotels() {
        hotelRepo.deleteAll();
    }

    @DeleteMapping("/rooms/deleteAll")
    public void deleteAllRooms() {
        roomRepo.deleteAll();
    }
}