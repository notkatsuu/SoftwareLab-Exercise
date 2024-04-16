import React from 'react';

function RoomDetails({ room }) {
    return (
        <div>
            <h2>Room Details</h2>
            <p>ID: {room.id}</p>
            <p>Number: {room.number + 1}</p>
            <p>Booked by: {room.booker ? room.booker : 'No one'}</p>
        </div>
    );
}

export default RoomDetails;