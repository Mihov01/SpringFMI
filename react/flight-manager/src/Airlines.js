import React, { useState, useEffect } from 'react';
import axios from 'axios';

function Airlines() {
  const [airlines, setAirlines] = useState([]);

  useEffect(() => {
    async function fetchData() {
      try {
        const response = await axios.get('http://localhost:8080/flight_manager');
        setAirlines(response.data);
      } catch (error) {
        console.error('Error fetching data: ', error);
      }
    }
    fetchData();
  }, []);

  return (
    <div>
      <h2>Airlines</h2>
      <ul>
        {airlines.map((airline) => (
          <li key={airline.id}>
            {airline.name} - {airline.country}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default Airlines;
