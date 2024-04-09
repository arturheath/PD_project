import React, { useEffect, useState } from 'react';
import logo from './logo.svg';
import './App.css';

function App() {

  const [healthStatus, setHealthStatus] = useState(null);

  console.log('healthStatus', healthStatus);

  useEffect(() => {
    fetch('http://localhost:8080/api/health')
      .then(response => response.text()) // use .text() instead of .json()
      .then(data => {
        console.log('data', data);
        setHealthStatus(data);
      })
      .catch(error => console.error('Error:', error));
  }, []);

  return (
    <div className="App">
      {healthStatus && <p>{JSON.stringify(healthStatus)}</p>}
    </div>
  );
}

export default App;
