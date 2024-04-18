import React, { useState } from 'react';

const RoomBooking = ({ hotelId }) => {

    const [roomNumber, setRoomNumber] = useState('');
    const [guestName, setGuestName] = useState('');
    const [booked, setBooked] = useState(false);

    const handleRoomNumberChange = (event) => {
        setRoomNumber(event.target.value);
    }

    const handleGuestNameChange = (event) => {
        setGuestName(event.target.value);
    }

    const handleBooking = () => {
        fetch(`http://localhost:8080/api/hotels/${hotelId}/rooms/${roomNumber}/book`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(guestName) // send guestName as a plain string
        })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                setBooked(true);
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }





    return (
        <div>
            <h2>Room Booking</h2>
            <input type="number" placeholder="Room Number" value={roomNumber} onChange={handleRoomNumberChange} />
            <input type="text" placeholder="Guest Name" value={guestName} onChange={handleGuestNameChange} />
            <button onClick={handleBooking}>Book Room</button>
            {booked && <p>Room booked successfully!</p>}
        </div>
    );
}

export default RoomBooking;