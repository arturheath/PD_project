import React, {useEffect, useState} from 'react';
import './App.css'
import CardsContainer from "./components/CardsContainer/cardsContainer.jsx";
import MovieInfo from "./components/MovieContainer/MovieInfo.jsx";
import {API_URL} from "./config.js";
import {Dialog, DialogContent, DialogHeader, DialogTrigger} from "./components/ui/dialog.jsx";
import MovieCreateForm from "./components/MovieCreateForm/MovieCreateForm.jsx";


function App() {

    const [selectedMovie, setSelectedMovie] = useState(null)
    const [movies, setMovies] = useState([])
    const [key, setKey] = useState(0)
    const [showCreateForm, setShowCreateForm] = useState(false)

    useEffect(() => {
        fetch(`${API_URL}/movies`)
            .then(response => response.json())
            .then(data => setMovies(data))
            .catch(error => console.error('ERROR', error));
    }, [])

    const handleMovieSelection = (id) => {
        setSelectedMovie(id)
    }

    const handleMovieUpdate = (updateMovie) => {
        setMovies(movies.map(movie => movie.id === updateMovie.id ? updateMovie : movie));
        setKey(prevKey => prevKey + 1)
    }

    const handleMovieCreate = (newMovie) => {
        setMovies([...movies, newMovie]);
    }

    function handleModal() {
        if (!showCreateForm) {
            setShowCreateForm(true);
        }
    }

    return (
        <>
            <Dialog open={showCreateForm}>
                <DialogTrigger className='bg-blue-500 text-white px-2 py-1 rounded'
                               onClick={handleModal}>
                    Add Movie
                </DialogTrigger>
                <DialogContent>
                    {showCreateForm &&
                        <MovieCreateForm setShowForm={setShowCreateForm} onMovieCreate={handleMovieCreate} setSelectedMovie={setSelectedMovie}/>}
                </DialogContent>
            </Dialog>
            <div className='flex flex-wrap'>
                <div className='w-1/4'>
                    <CardsContainer movies={movies}
                                    onMovieClick={handleMovieSelection}
                                    setMovies={setMovies}
                                    setSelectedMovie={setSelectedMovie}
                    />
                </div>
                <div className='w-3/4'>
                    <MovieInfo key={key} id={selectedMovie} onMovieUpdate={handleMovieUpdate}/>
                </div>
            </div>
        </>
    )
}

export default App
