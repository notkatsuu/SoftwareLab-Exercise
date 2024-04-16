package com.example.restservice.persistence;

import com.example.restservice.domain.Room;

import java.util.ArrayList;
import java.util.List;


public class Hotel {

    private static Hotel instance;

    private String name = null;
    private static final int NUMBER_OF_ROOMS = 25;
    private final List<Room> rooms = new ArrayList<>(NUMBER_OF_ROOMS);


    public static Hotel getInstance() {
        if (instance == null) {
            instance = new Hotel();
            for (int i = 0; i < NUMBER_OF_ROOMS; i++) {
                try {
                    instance.rooms.add(new Room(i));
                } catch (Exception e) {
                    System.out.println("Error creating hotel rooms");
                }
            }
        }
        return instance;
    }

    public void setHotelName(String name) {
        this.name = name;
    }

    public Room getRoom(int index) {
        return rooms.get(index);
    }

    public List<Room> getAllRooms() {
        return rooms;
    }

    public String getHotelName() {
        return name;
    }
}
