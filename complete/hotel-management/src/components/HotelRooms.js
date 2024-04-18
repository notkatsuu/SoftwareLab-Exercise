import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import RoomBooking from './RoomBooking'; // Import the RoomBooking component
import './HotelRooms.css';

const HotelRooms = () => {
    const { id } = useParams();
    const [rooms, setRooms] = useState([]);

    useEffect(() => {
        fetch(`http://localhost:8080/api/hotels/${id}/rooms`)
            .then(response => response.json())
            .then(data => {
                data.sort((a, b) => a.number - b.number);
                setRooms(data);
            });
    }, [id]);

    return (
        <div>
            <h1>Hotel Rooms</h1>
            <RoomBooking hotelId={id} /> {/* Use the RoomBooking component */}
            <br/>

            <div className="room-grid">
                {rooms.length > 0 ? (
                    rooms.map(room => (
                        <div key={room.id} className={`room-item ${room.booker ? 'booked' : 'unbooked'}`}>
                            <p>{room.number+1}</p>
                        </div>
                    ))
                ) : (
                    <p>No rooms available for this hotel.</p>
                )}
            </div>
        </div>
    );
}

export default HotelRooms;