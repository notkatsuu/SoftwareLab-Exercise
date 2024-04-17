package com.example.restservice.application.dto;

public class HotelDTO {

        private String name;

        private Long id;

        public HotelDTO(Long id, String name) {
            this.id = id;
            this.name = name;
        }


        public String getName() {
            return name;
        }

}
