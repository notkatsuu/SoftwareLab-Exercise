package com.example.restservice.application;

import com.example.restservice.persistence.Hotel;
import com.example.restservice.domain.Room;
import com.example.restservice.application.dto.RoomDTO;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class Controller {

    private final Hotel hotel = Hotel.getInstance();

    public RoomDTO getRoom(int num) {
        Room room = hotel.getRoom(num);
        return new RoomDTO(room.getId(), room.getNumber(), room.getBooker());
    }

    public List<RoomDTO> getAllRooms() {
        return hotel.getAllRooms().stream()
                .map(a -> new RoomDTO(a.getId(), a.getNumber(), a.getBooker()))
                .toList();
    }

    public List<RoomDTO> getAvailableRooms() {
        return hotel.getAllRooms().stream()
                .filter(a -> !a.isBooked())
                .map(a -> new RoomDTO(a.getId(), a.getNumber(), a.getBooker()))
                .toList();
    }

    public void setHotelName(String name) {
        hotel.setHotelName(name);
    }

    public void setRoomBooker(int index, String booker) {
        hotel.getRoom(index).setBooker(booker);
    }


    public String getHotelName() {
        return hotel.getHotelName();
    }
}
