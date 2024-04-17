package com.example.restservice.domain;

import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;

@Entity
public class Hotel {


    @Column
    private String name;
    private static final int NUMBER_OF_ROOMS = 25;



    @OneToMany(mappedBy = "hotel")
    private final List<Room> rooms = new ArrayList<>(NUMBER_OF_ROOMS);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public Hotel() throws Exception {
        for (int i = 0; i < NUMBER_OF_ROOMS; i++) {
            rooms.add(new Room(i));
        }
    }



    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
