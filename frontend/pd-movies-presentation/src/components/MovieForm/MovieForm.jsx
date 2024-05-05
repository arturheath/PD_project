"use client";

import {useEffect, useState} from "react";
import {zodResolver} from "@hookform/resolvers/zod";
import {useForm} from "react-hook-form";
import {z} from 'zod';
import {Form, FormControl, FormField, FormItem, FormLabel} from "../ui/form.jsx";
import {Input} from "../ui/input.jsx";
import {Button} from "../ui/button.jsx";
import {Textarea} from "../ui/textarea.jsx";
import categories from "../../mockedData/categories.js";
import {Select, SelectContent, SelectItem, SelectTrigger, SelectValue} from "../ui/select.jsx";
import {API_URL} from "../../config.js";
import MultiSelect from "react-select";

const formSchema = z.object({
    name: z.string(),
    year: z.string().refine(value => value.length === 4),
    description: z.string(),
    category: z.string(),
    banner: z.string(),
    persons: z.array(z.string()),
});

const MovieForm = ({movieInfo, setShowForm, onMovieUpdate}) => {

    const [selectedPersons, setSelectedPersons] = useState([]);
    const [personOptions, setPersonOptions] = useState([]);

    useEffect(() => {
        fetch(`${API_URL}/people`)
            .then(response => response.json())
            .then(data => {
                const options = data.map(person => ({
                    value: person.id, label: person.firstName + " " + person.lastName
                }));
                setPersonOptions(options);

                // Set selected persons
                const selected = movieInfo?.persons.map(person => ({
                    value: person.id, label: person.firstName + " " + person.lastName
                })) || [];
                setSelectedPersons(selected);
            })
            .catch(error => console.error('ERROR', error));
    }, []);

    const form = useForm({
        resolver: zodResolver(formSchema),
        mode: "onSubmit",
        defaultValues: {
            name: movieInfo?.name || "",
            year: movieInfo?.year.toString() || "",
            description: movieInfo?.description || "",
            category: movieInfo?.category || "",
            banner: movieInfo?.banner || "",
            persons: movieInfo?.persons.map(person => person.id) || [],
        }
    });

    const onSubmit = async (data) => {
        console.log("sending data to edit movie id: " + movieInfo.id);

        const dataWithPersons = {...data, persons: selectedPersons.map(person => person.value)};
        console.log("data", dataWithPersons);

        try {
            const response = await fetch(`${API_URL}/movies/${movieInfo.id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(dataWithPersons)
            });

            if (response.ok) {
                console.log("Movie updated successfully");
                const updatedMovie = await response.json();
                onMovieUpdate(updatedMovie);
                setShowForm(false);
            } else {
                console.error("Movie update failed");
            }
        } catch (error) {
            console.error("ERROR", error);
        }
    }

    return (
        <Form {...form}>
            <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-8">
                <FormField
                    control={form.control}
                    name="name"
                    render={({field}) => (
                        <FormItem>
                            <FormLabel>Name</FormLabel>
                            <FormControl>
                                <Input {...field} />
                            </FormControl>
                        </FormItem>
                    )}
                />
                <FormField
                    control={form.control}
                    name="year"
                    render={({field}) => (
                        <FormItem>
                            <FormLabel>Year</FormLabel>
                            <FormControl>
                                <Input {...field} />
                            </FormControl>
                        </FormItem>
                    )}
                />

                <FormField control={form.control} name="description" render={({field}) => (
                    <FormItem>
                        <FormLabel>Description</FormLabel>
                        <FormControl>
                            <Textarea {...field} />
                        </FormControl>
                    </FormItem>
                )}/>

                <FormField
                    control={form.control}
                    name="banner"
                    render={({field}) => (
                        <FormItem>
                            <FormLabel>Banner URL</FormLabel>
                            <FormControl>
                                <Input {...field} />
                            </FormControl>
                        </FormItem>
                    )}
                />

                <FormItem>
                    <FormLabel>Persons</FormLabel>
                    <FormControl>
                        <MultiSelect
                            isMulti
                            value={selectedPersons}
                            options={personOptions}
                            onChange={setSelectedPersons}
                        ></MultiSelect>
                    </FormControl>
                </FormItem>

                <FormField control={form.control} name="category" render={({field}) => (
                    <FormItem>
                        <FormLabel>Category</FormLabel>
                        <FormControl>
                            <Select onValueChange={value => {
                                field.onChange(value);
                            }} value={field.value}
                            >
                                <SelectTrigger>
                                    <SelectValue>
                                        {field.value || "Select a category"}
                                    </SelectValue>
                                </SelectTrigger>
                                <SelectContent>
                                    {categories.map(category => (
                                        <SelectItem key={category.id} value={category.name}>{category.name}</SelectItem>
                                    ))}
                                </SelectContent>
                            </Select>
                        </FormControl>
                    </FormItem>
                )}
                />
                <div>
                    <Button className="m-2" variant="destructive" onClick={() => setShowForm(false)}>Cancel</Button>
                    <Button className="m-2" type="submit">Submit</Button>
                </div>
            </form>
        </Form>

    );
}

export default MovieForm;