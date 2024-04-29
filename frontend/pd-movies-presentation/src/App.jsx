import React, {useEffect, useState} from 'react';
import './App.css'
import CardsContainer from "./components/CardsContainer/cardsContainer.jsx";
import MovieInfo from "./components/MovieContainer/MovieInfo.jsx";
import {API_URL} from "./config.js";

function App() {

    const [selectedMovie, setSelectedMovie] = useState(null)
    const [movies, setMovies] = useState([])

    useEffect(() => {
        console.log("requesting list of movies")
        fetch(`${API_URL}/movies`)
            .then(response => response.json())
            .then(data => setMovies(data))
            .catch(error => console.error('ERROR', error));

        console.log("Movies from DB", movies)
    }, [])

    console.log("selectedMovie", selectedMovie)

    const handleMovieSelection = (id) => {
        setSelectedMovie(id)
    }

    return (
        <>
            <div className='flex flex-wrap'>
                <div className='w-1/4'>
                    <CardsContainer movies={movies}
                                    onMovieClick={handleMovieSelection}
                                    setMovies={setMovies}/>
                </div>
                <div className='w-3/4'>
                    <MovieInfo id={selectedMovie}/>
                </div>
            </div>
        </>
    )
}

export default App
