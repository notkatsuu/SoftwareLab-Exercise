import React from 'react';
import './App.css';
import HotelGrid from './components/HotelGrid';
import HotelRooms from './components/HotelRooms'; // Import the component that will display the rooms of a hotel
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/hotels/:id" element={<HotelRooms />} />
          <Route path="/" element={<HotelGrid />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;