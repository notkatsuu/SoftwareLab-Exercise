package com.example.restservice.api;

import com.example.restservice.application.dto.RoomDTO;
import com.example.restservice.application.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final Controller controller;
    public ApiController(Controller controller) {
        this.controller = controller;
    }

    @GetMapping("/")
    @ResponseBody
    public String index() {
        return "Welcome to the Hotel Management API!";
    }
    @GetMapping("/rooms")
    public ResponseEntity<List<RoomDTO>> getAllRooms() {
        return new ResponseEntity<>(controller.getAllRooms(), HttpStatus.OK);
    }
    @GetMapping("/rooms/{id}")
    public ResponseEntity<RoomDTO> getRoom(@PathVariable int id) {
        return new ResponseEntity<>(controller.getRoom(id), HttpStatus.OK);
    }

    @GetMapping("/hotel")
    public ResponseEntity<String> getHotelName() {
        return new ResponseEntity<>(controller.getHotelName(), HttpStatus.OK);
    }
    @PostMapping("/rooms/{id}/booking")
    public ResponseEntity<Void> bookRoom(@PathVariable int id, @RequestBody String booker) {
        controller.setRoomBooker(id, booker);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/hotel")
    public ResponseEntity<Void> setHotelName(@RequestBody String name) {
        controller.setHotelName(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/rooms/booking")
    public ResponseEntity<Void> bookRooms(@RequestBody BookingRequest request) {
        try {
            for (int i = 0; i < request.getNumRooms(); i++) {
                for (RoomDTO room : controller.getAvailableRooms()) {
                    controller.setRoomBooker(room.getNumber(), request.getBooker());
                    break;
                }
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/rooms/{id}/booking")
    public ResponseEntity<Void> unbookRoom(@PathVariable int id) {
        controller.setRoomBooker(id, null);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}