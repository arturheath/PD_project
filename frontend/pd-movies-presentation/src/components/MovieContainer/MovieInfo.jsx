import React, {useEffect, useState} from 'react';
import {Card, CardContent, CardDescription, CardHeader, CardTitle} from "../ui/card.jsx";
import {Badge} from "../ui/badge.jsx";
import {Dialog, DialogContent, DialogTrigger} from "../ui/dialog.jsx";
import PersonsModal from "../PersonsModal/PersonsModal.jsx";
import MovieForm from "../MovieForm/MovieForm.jsx";
import {API_URL} from "../../config.js";

/*const movieInfo = {
    id: 1,
    title: 'The Godfather',
    year: '1972',
    genre: {
        id: 2,
        name: "Crime"
    },
    description: 'Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family. When organized crime family patriarch, Vito Corleone barely survives an attempt on his life, his youngest son, Michael steps in to take care of the would-be killers, launching a campaign of bloody revenge.',
    poster: 'https://image.tmdb.org/t/p/w600_and_h900_bestv2/oJagOzBu9Rdd9BrciseCm3U3MCU.jpg',
}*/

const style = "text-center bg-blue-500 text-white px-2 py-1 rounded inline-flex items-center";

const MovieInfo = ({id}) => {

    const [showForm, setShowForm] = useState(false);
    const [movieInfo, setMovieInfo] = useState({});

    useEffect(() => {
        console.log("requesting movie details id: ", id)
        fetch(`${API_URL}/movies/${id}`)
            .then(response => response.json())
            .then(data => setMovieInfo(data))
            .catch(error => console.error('ERROR', error));
    }, [id]);

    console.log("id para fazer pedido de detalhes", id)


    function handleModal() {
        setShowForm(!showForm);
    }

    return (
        <>
            {
                id &&
                <Card className='h-screen flex flex-wrap'>
                    <CardHeader className='w-1/2'>
                        <CardTitle className='text-2xl text-center'>{movieInfo.name}</CardTitle>
                        <CardDescription className='text-2xl text-center'>{movieInfo.year}</CardDescription>
                        <div className='flex justify-center'>
                            <Badge
                                className='text-center px-2 py-1 rounded inline-flex items-center'>{movieInfo.category}</Badge>
                        </div>
                        <p className='p-1'>{movieInfo.description}</p>
                        <div className='flex justify-center space-x-4'>
                            <Dialog>
                                <DialogTrigger className={style}>Directors </DialogTrigger>
                                <DialogContent>
                                    <PersonsModal id={movieInfo.id} role='director'/>
                                </DialogContent>
                            </Dialog>
                            <Dialog>
                                <DialogTrigger className={style}>Producers</DialogTrigger>
                                <DialogContent>
                                    <PersonsModal id={movieInfo.id} role='producer'/>
                                </DialogContent>
                            </Dialog>
                            <Dialog>
                                <DialogTrigger className={style}>Cast</DialogTrigger>
                                <DialogContent>
                                    <PersonsModal id={movieInfo.id} role='cast'/>
                                </DialogContent>
                            </Dialog>
                        </div>
                        <div className='p-10 flex justify-center space-x-4'>
                            <Dialog>
                                <DialogTrigger className='bg-blue-500 text-white px-2 py-1 rounded'
                                               onClick={handleModal}>Edit Movie</DialogTrigger>
                                <DialogContent>
                                    {showForm && <MovieForm movieInfo={movieInfo}/>}
                                </DialogContent>
                            </Dialog>
                        </div>
                    </CardHeader>
                    <CardContent className='w-1/2 p-1'>
                        <img className='h-screen' src={movieInfo.banner} alt='poster'/>
                    </CardContent>
                </Card>
            }
            {
                showForm && <MovieForm/>
            }
        </>
    );
}

export default MovieInfo;