import React, {useEffect} from 'react';
import {Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle} from "../ui/card.jsx";
import {Badge} from "../ui/badge.jsx";
import {Dialog, DialogContent, DialogTrigger} from "../ui/dialog.jsx";
import PersonsModal from "../PersonsModal/PersonsModal.jsx";
import {
    AlertDialog,
    AlertDialogAction,
    AlertDialogCancel,
    AlertDialogContent,
    AlertDialogFooter,
    AlertDialogHeader,
    AlertDialogTrigger
} from "../ui/alert-dialog.jsx";

const movieInfo = {
    id: 1,
    title: 'The Godfather',
    year: '1972',
    genre: 'Crime',
    description: 'Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family. When organized crime family patriarch, Vito Corleone barely survives an attempt on his life, his youngest son, Michael steps in to take care of the would-be killers, launching a campaign of bloody revenge.',
    poster: 'https://image.tmdb.org/t/p/w600_and_h900_bestv2/oJagOzBu9Rdd9BrciseCm3U3MCU.jpg',
}

const style = "text-center bg-blue-500 text-white px-2 py-1 rounded inline-flex items-center";

const MovieInfo = ({id}) => {

    useEffect(() => {
        console.log("id para fazer pedido de detalhes", id)
        console.log("requesting movie details", movieInfo)
        // GET /movies/:id
    }, []);

    console.log("id para fazer pedido de detalhes", id)

    return (
        <>
            {
                id &&
                <Card className='h-screen flex flex-wrap'>
                    <CardHeader className='w-1/2'>
                        <CardTitle className='text-2xl text-center'>{movieInfo.title}</CardTitle>
                        <CardDescription className='text-2xl text-center'>{movieInfo.year}</CardDescription>
                        <div className='flex justify-center'>
                            <Badge
                                className='text-center px-2 py-1 rounded inline-flex items-center'>{movieInfo.genre}</Badge>
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
                    </CardHeader>
                    <CardContent className='w-1/2 p-1'>
                        <img className='h-screen' src={movieInfo.poster} alt='poster'/>
                    </CardContent>

                </Card>
            }
        </>
    );
}

export default MovieInfo;