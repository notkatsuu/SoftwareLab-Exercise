package com.example.restservice.domain;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

@Entity
public class Room {

    @Id
    private final String id = UUID.randomUUID().toString();

    @Column
    private final int number;

    @Column
    private String booker;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public Room(int number) throws Exception {
        checkNumber(number);
        this.number = number;
    }

    protected Room() {
        this.number = 0;
    }

    private void checkNumber(int number) throws Exception {
        if (number < 0) throw new Exception();
    }

    @JsonProperty
    public int getNumber() {
        return number;
    }

    @JsonProperty
    public String getId() {
        return id;
    }

    public String getBooker() {
        return booker;
    }

    public void setBooker(String booker) {
        this.booker = booker;
    }

    public boolean isBooked() {
        return booker != null;
    }

    public void setHotel(Hotel savedHotel) {
        this.hotel = savedHotel;
    }
}