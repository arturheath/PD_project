import React, {useState, useEffect} from 'react';
import './App.css'
import CardsContainer from "./components/CardsContainer/cardsContainer.jsx";
import MovieInfo from "./components/MovieContainer/MovieInfo.jsx";
import movies from "./mockedData/movieData.js";


function App() {

    // useEffect para carregar a lista de filmes

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
        <>
            <div className='flex flex-wrap'>
                <div className='w-1/2'>
                    <CardsContainer movies={movies} onMovieClick={handleMovieSelection}/>
                </div>
                <div className='w-1/2'>
                    <MovieInfo id={selectedMovie}/>
                </div>
            </div>
        </>
    )
}

export default App
