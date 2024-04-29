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

const CardsContainer = ({movies, onMovieClick}) => {

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
    const handleDelete = (id) => {
        console.log("deleting movie", id)
        // DELETE /movies/:id
    }


    return (
        <ScrollArea className="h-screen rounded-md border p-4">
            {
                movies.map(movie => (
                    <Card className='m-2 bg-gray-200 cursor-pointer' onClick={() => onMovieClick(movie.id)}>
                        <CardHeader>
                            <CardTitle>{movie.title}</CardTitle>
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