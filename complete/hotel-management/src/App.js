import React, { useState, useEffect } from 'react';
import axios from 'axios';
import HotelNameInput from './HotelNameInput';
import RoomDetails from './RoomDetails';

function App() {
    const [hotelName, setHotelName] = useState(localStorage.getItem('hotelName') || null);
    const [rooms, setRooms] = useState([]);
    const [booker, setBooker] = useState('');
    const [numRooms, setNumRooms] = useState(0);
    const [selectedRoom, setSelectedRoom] = useState(null);
    const [error, setError] = useState('');

    useEffect(() => {
        axios.get('http://localhost:8080/api/hotel')
            .then(response => {
                if (response.data) {
                    setHotelName(response.data);
                    localStorage.setItem('hotelName', response.data);
                }
            })
            .catch(error => {
                console.error('Error fetching hotel name:', error);
            });
    }, []);

    useEffect(() => {
        if (hotelName) {
            axios.post('http://localhost:8080/api/hotel', hotelName)
                .then(() => {
                    return axios.get('http://localhost:8080/api/rooms');
                })
                .then(response => {
                    setRooms(response.data);
                });
        }
    }, [hotelName]);

    const bookRooms = (event) => {
        event.preventDefault();
        const availableRooms = rooms.filter(room => !room.booker).length;
        if (numRooms > availableRooms) {
            setError(`Only ${availableRooms} rooms are available.`);
            return;
        }
        axios.post('http://localhost:8080/api/rooms/booking', {
            numRooms,
            booker
        }).then(() => {
            setBooker('');
            setNumRooms(0);
            return axios.get('http://localhost:8080/api/rooms');
        }).then(response => {
            setRooms(response.data);
        });
    };

    if (!hotelName) {
        return <HotelNameInput setHotelName={setHotelName} />;
    }

    return (
        <div>
            <h1 style={{textAlign: 'center'}}>{hotelName} Hotel Management</h1>
            <form onSubmit={bookRooms} style={{textAlign: 'center'}}>
                <label>
                    Your name:
                    <input type="text" value={booker} onChange={e => setBooker(e.target.value)} required />
                </label>
                <br/>
                <br/>
                <label>
                    Number of rooms to book:
                    <input type="number" value={numRooms} onChange={e => setNumRooms(e.target.value)} required />
                </label>
                <button type="submit">Book Rooms</button>
            </form>
            {error && <p style={{ textAlign: 'center',color: 'red' }}>{error}</p>}
            <br/>
            <div style={{display: 'grid', gridTemplateColumns: 'repeat(auto-fill, minmax(40px, 1fr))', gap: '10px'}}>
                {rooms.map(room => (
                    <div key={room.id}
                         style={{ textAlign: 'center', padding: '10px', color: 'white', backgroundColor: room.booker ? 'red' : 'green'}}
                         onClick={() => setSelectedRoom(room)}>
                        {room.number + 1}
                    </div>
                ))}
            </div>
            {selectedRoom && <RoomDetails room={selectedRoom} />}
        </div>
    );
}

export default App;