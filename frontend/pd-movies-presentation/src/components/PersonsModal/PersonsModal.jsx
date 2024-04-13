import React, {useEffect, useState} from 'react';
import {Card, CardContent, CardHeader, CardTitle} from "../ui/card.jsx";
import {Dialog, DialogContent, DialogTrigger} from "../ui/dialog.jsx";
import people from "../../mockedData/peopleData.js";


const PersonsModal = ({id, role}) => {

    switch (role) {
        case 'director':
            console.log("Resquesting directors");
            break;
        case 'producer':
            console.log("Resquesting producers");
            break;
        case 'cast':
            console.log("Resquesting cast");
            break;
        default:
            console.log("Invalid role");
    }

    useEffect(() => {
        console.log("id para fazer pedido de pessoas", id)
        console.log("requesting people details", people)

        //GET /movies/{movie_id}/directors
        // GET /movies/{movie_id}/producers
        // GET /movies/{movie_id}/cast

    }, []);

    return (
        <div className='flex justify-center space-x-4'>
            {
                people.map(person => (
                    <Dialog key={person.id}>
                        <DialogTrigger className='cursor-pointer'>
                            <Card className='cursor-pointer'>
                                <CardHeader>
                                    <CardTitle>{person.name}</CardTitle>
                                    <CardContent>
                                        <img src={person.profile_path} alt='profile'/>
                                    </CardContent>
                                </CardHeader>
                            </Card>
                        </DialogTrigger>
                    </Dialog>
                ))}
        </div>
    );
}

export default PersonsModal;