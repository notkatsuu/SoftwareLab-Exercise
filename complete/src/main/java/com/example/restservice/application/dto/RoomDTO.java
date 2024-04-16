package com.example.restservice.application.dto;

public class RoomDTO {

    private final String id;

    private final int number;

    private final String booker;

    public RoomDTO(String id, int number, String booker) {
        this.id = id;
        this.number = number;
        this.booker = booker;
    }

    public String getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public String getBooker() {
        return booker;
    }

    public boolean isBooked() {
        return booker != null;
    }








}
