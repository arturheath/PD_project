import React, {useEffect, useState} from 'react';
import './App.css'
import CardsContainer from "./components/CardsContainer/cardsContainer.jsx";
import MovieInfo from "./components/MovieContainer/MovieInfo.jsx";
import movies from "./mockedData/movieData.js";


function App() {

    const [selectedMovie, setSelectedMovie] = useState(null)

    useEffect(() => {
        console.log("requesting list of movies", movies)
        //GET /movies
    }, [])

    console.log("selectedMovie", selectedMovie)

    const handleMovieSelection = (id) => {
        setSelectedMovie(id)
    }

    return (
        <div className='flex flex-wrap'>
            <div className='w-1/4'>
                <CardsContainer movies={movies} onMovieClick={handleMovieSelection}/>
            </div>
            <div className='w-3/4'>
                <MovieInfo id={selectedMovie}/>
            </div>
        </div>
    )
}

export default App
