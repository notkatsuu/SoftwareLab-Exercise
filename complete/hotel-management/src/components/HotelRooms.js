import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

const HotelRooms = () => {
    const { id } = useParams();
    const [rooms, setRooms] = useState([]);

    useEffect(() => {
        fetch(`http://localhost:8080/api/hotels/${id}/rooms`)
            .then(response => response.json())
            .then(data => setRooms(data));
    }, [id]);

    return (
        <div>
            <h1>Hotel Rooms</h1>
            <div className="hotel-rooms">
                {rooms.length > 0 ? (
                    rooms.map(room => (
                        <div key={room.id} className="room-item">
                            <p>Room Number: {room.number}</p>
                            <p>Booker: {room.booker ? room.booker : "No one"}</p>
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