import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import './HotelGrid.css';

const HotelGrid = () => {
    const [hotels, setHotels] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8080/api/hotels')
            .then(response => response.json())
            .then(data => {
                console.log(data); // Log the fetched data
                setHotels(data);
            });
    }, []);

    return (
        <div>
            <h1>Hotel Grid</h1>
            <div className="hotel-grid">
                {hotels.map(hotel => (
                    <div key={hotel.id} className="hotel-item">
                        <Link to={`/hotels/${hotel.id}`}>{hotel.name}</Link>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default HotelGrid;