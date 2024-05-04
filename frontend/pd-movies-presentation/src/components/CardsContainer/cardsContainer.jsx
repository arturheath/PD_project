import {ScrollArea} from "../ui/scroll-area.jsx";
import {Card, CardDescription, CardFooter, CardHeader, CardTitle} from "../ui/card.jsx";
import {
    AlertDialog,
    AlertDialogAction,
    AlertDialogCancel,
    AlertDialogContent,
    AlertDialogFooter,
    AlertDialogHeader,
    AlertDialogTrigger
} from "../ui/alert-dialog.jsx";
import React from "react";
import {API_URL} from "../../config.js";

const CardsContainer = ({movies, onMovieClick, setMovies}) => {

    const renderAlertDialog = (id) => {
        return (
            <AlertDialog>
                <AlertDialogTrigger>Delete</AlertDialogTrigger>
                <AlertDialogContent>
                    <AlertDialogHeader>Are you sure you want to delete this movie?</AlertDialogHeader>
                    <AlertDialogFooter>
                        <AlertDialogCancel>Cancel</AlertDialogCancel>
                        <AlertDialogAction onClick={() => handleDelete(id)}>Delete</AlertDialogAction>
                    </AlertDialogFooter>
                </AlertDialogContent>
            </AlertDialog>
        );
    }
    const handleDelete = async (id) => {
        console.log("deleting movie", id)

        const response = await fetch(`${API_URL}/movies/${id}`, {
            method: 'DELETE',
        });

        if (!response.ok) {
            throw new Error('HTTP error, status = ' + response.status)
        } else {
            console.log(`Movie with id ${id} deleted successfully`)
            setMovies(movies.filter(movie => movie.id !== id))
        }
    }


    return (
        <ScrollArea className="h-screen rounded-md border p-4">
            {
                movies.map(movie => (
                    <Card key={movie.id} className='m-2 bg-gray-200 cursor-pointer'
                          onClick={() => onMovieClick(movie.id)}>
                        <CardHeader>
                            <CardTitle>{movie.name}</CardTitle>
                            <CardDescription>{movie.year}</CardDescription>
                        </CardHeader>
                        <CardFooter>
                            {renderAlertDialog(movie.id)}
                        </CardFooter>
                    </Card>
                ))
            }
        </ScrollArea>
    );
}

export default CardsContainer;