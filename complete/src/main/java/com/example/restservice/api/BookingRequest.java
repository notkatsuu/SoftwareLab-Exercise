package com.example.restservice.api;

public class BookingRequest {

    private int numRooms;
    private String booker;

    // Getters and Setters
    public int getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(int numRooms) {
        this.numRooms = numRooms;
    }

    public String getBooker() {
        return booker;
    }

    public void setBooker(String booker) {
        this.booker = booker;
    }
}