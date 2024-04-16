package com.example.restservice.domain;
import java.util.UUID;
public class Room {

    private final String id = UUID.randomUUID().toString();
    private final int number;

    private String booker;

    public Room(int number) throws Exception {
        checkNumber(number);
        this.number = number;
        this.booker = booker;
    }

    private void checkNumber(int number) throws Exception {
        if (number < 0) throw new Exception();
    }

    public int getNumber() {
        return number;
    }

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

}
