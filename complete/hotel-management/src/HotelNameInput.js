import React, { useState } from 'react';

function HotelNameInput({ setHotelName }) {
    const [input, setInput] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();
        setHotelName(input);
    };

    return (
        <div>
            <h1 style={{ textAlign: 'center' }}> Welcome to the Hotel Management System</h1>
            <form onSubmit={handleSubmit} style={{ textAlign: 'center' }}>
                <label>
                    Enter the hotel name:
                    <input type="text" value={input} onChange={e => setInput(e.target.value)} required />
                </label>
                <button type="submit">Submit</button>
            </form>
        </div>
    );
}

export default HotelNameInput;