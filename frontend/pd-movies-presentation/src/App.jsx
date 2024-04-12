import React, {useEffect, useState} from 'react';
import './App.css'

function App() {
    /*const [healthStatus, setHealthStatus] = useState(null);*/

    /*console.log('healthStatus', healthStatus);

    useEffect(() => {
        fetch('http://localhost:8080/api/health')
            .then(response => response.text())
            .then(data => {
                console.log('data', data);
                setHealthStatus(data);
            })
            .catch(error => console.error('Error:', error));
    }, []);*/

    return (
        <>
            {/*{healthStatus && <p>{JSON.stringify(healthStatus)}</p>}*/}
            <h1 className='bg-yellow-400'>PD Movies</h1>
        </>
    )
}

export default App
