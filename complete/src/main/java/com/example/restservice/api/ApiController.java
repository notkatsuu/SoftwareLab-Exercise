package com.example.restservice.api;

import com.example.restservice.domain.Hotel;
import com.example.restservice.domain.Room;
import com.example.restservice.repo.HotelRepo;
import com.example.restservice.repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/hotels/{hotelId}/rooms")
    public List<Room> getRoomsByHotelId(@PathVariable Long hotelId) {
        return roomRepo.findByHotelId(hotelId);
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


    @PostMapping("/hotels/{hotelId}/rooms/{numOfRooms}/book")
    public Room bookRoom(@PathVariable int numOfRooms, @RequestBody String booker, @PathVariable String hotelId) {
        //Book the number of rooms specified by roomNumber, if roomNumber is greater than the number of rooms in the hotel, throw an exception

        Hotel hotel = hotelRepo.findById(Long.parseLong(hotelId)).orElseThrow();
        List<Room> rooms = roomRepo.findByHotelId(Long.parseLong(hotelId));
        //count the number of rooms that are not booked and order them by their number
        List<Room> availableRooms = rooms.stream().filter(room -> !room.isBooked()).sorted((r1, r2) -> r1.getNumber() - r2.getNumber()).toList();

        if (numOfRooms > availableRooms.size()) {
            throw new IllegalArgumentException("Number of rooms to book is greater than the number of available rooms");
        }
        //book the first numOfRooms rooms that are not booked, ordered by num, starting from room 0 and up
        for (Room room : availableRooms) {
            if (!room.isBooked()) {
                room.setBooker(booker);
                roomRepo.save(room);
                numOfRooms--;
            }
            if (numOfRooms == 0) {
                break;
            }
        }

        return null;


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