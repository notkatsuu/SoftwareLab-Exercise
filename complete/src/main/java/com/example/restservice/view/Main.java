package com.example.restservice.view;


import java.util.List;
import java.util.Scanner;

import com.example.restservice.application.dto.RoomDTO;
import com.example.restservice.application.Controller;


public class Main {

    static Scanner scanner = new Scanner(System.in);
    static Controller controller = new Controller();
    static boolean runtime = true;

    public static void main(String[] args) {
        String name = askForInput("Quin es el nom de l'hotel on vols fer la reserva: ", scanner);
        controller.setHotelName(name);
        while (runtime)
            try {
                Menu();
            } catch (RuntimeException e) {
                System.out.print(e.getMessage());
            }
    }

    public static String askForInput(String message, Scanner scanner) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public static void askForRooms(int num, String booker) throws RuntimeException {
        if (num>controller.getAvailableRooms().size()) 
            throw new RuntimeException("No hi ha suficients habitacions disponibles");
        for (int i = 0; i < num; i++) {
            for (RoomDTO room : controller.getAvailableRooms()) {
                System.out.print("Reservant habitació " + (room.getNumber() + 1) + "...\n");
                controller.setRoomBooker(room.getNumber(), booker);
                break;
            }
        }
        System.out.print(listRooms());

    }
    public static String listRooms() {
        List<RoomDTO> rooms = controller.getAllRooms();
        StringBuilder sb = new StringBuilder();
        for (RoomDTO room : rooms) {
                sb.append("● Room ").append(room.getNumber()+1).append(": Booked by the ").append(room.getBooker()).append(" family\n");
        }
        return sb.toString();
    }

    public static void Menu() {
        String option = askForInput("Quina opció vols escollir? \n\t1. Reservar habitació \n\t2. Evacuar habitació \n\t3. Sortir\n ", scanner);
        switch (option) {
            case "1" -> askForRooms(Integer.parseInt(askForInput("Quantes habitacions vols reservar? ", scanner)),
                    askForInput("-\tIntrodueix el teu nom: ", scanner));
            case "2" -> leaveRoom();
            case "3" -> runtime = false;
            default -> System.out.println("Opció no vàlida");
        }
    }

    public static void leaveRoom() {
        int roomToLeave = Integer.parseInt(askForInput("Quina habitació vols evacuar? ", scanner));
        if (roomToLeave < 0 || roomToLeave > controller.getAllRooms().size()) {
            throw new RuntimeException("Habitació no vàlida");
        }
        if (!controller.getRoom(roomToLeave).isBooked()) {
            throw new RuntimeException("La habitació ja està lliure");
        }
        controller.setRoomBooker(roomToLeave, null);
    }
}
