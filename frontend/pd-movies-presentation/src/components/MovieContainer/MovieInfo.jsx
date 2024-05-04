import React, {useEffect, useState} from 'react';
import {Card, CardContent, CardDescription, CardHeader, CardTitle} from "../ui/card.jsx";
import {Badge} from "../ui/badge.jsx";
import {Dialog, DialogContent, DialogTrigger} from "../ui/dialog.jsx";
import PersonsModal from "../PersonsModal/PersonsModal.jsx";
import MovieForm from "../MovieForm/MovieForm.jsx";
import {API_URL} from "../../config.js";

const style = "text-center bg-blue-500 text-white px-2 py-1 rounded inline-flex items-center";

const MovieInfo = ({id, onMovieUpdate}) => {

    const [showForm, setShowForm] = useState(false);
    const [movieInfo, setMovieInfo] = useState({});

    useEffect(() => {
        if (id) {
            console.log("requesting movie details id: ", id)
            fetch(`${API_URL}/movies/${id}`)
                .then(response => response.json())
                .then(data => setMovieInfo(data))
                .catch(error => console.error('ERROR', error));
        }
    }, [id]);

    function handleModal() {
        if (!showForm) {
            setShowForm(true);
        }
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
                            <Dialog open={showForm}>
                                <DialogTrigger className='bg-blue-500 text-white px-2 py-1 rounded'
                                               onClick={handleModal}>Edit Movie</DialogTrigger>
                                <DialogContent>
                                    {showForm && <MovieForm movieInfo={movieInfo} setShowForm={setShowForm}
                                                            onMovieUpdate={onMovieUpdate}/>}
                                </DialogContent>
                            </Dialog>
                        </div>
                    </CardHeader>
                    <CardContent className='w-1/2 p-1'>
                        <img className='h-screen' src={movieInfo.banner} alt='poster'/>
                    </CardContent>
                </Card>
            }
        </>
    );
}

export default MovieInfo;