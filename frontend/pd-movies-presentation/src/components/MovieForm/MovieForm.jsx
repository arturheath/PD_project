"use client";

import {zodResolver} from "@hookform/resolvers/zod";
import {useForm} from "react-hook-form";
import {z} from 'zod';
import {Form, FormControl, FormField, FormItem, FormLabel} from "../ui/form.jsx";
import {Input} from "../ui/input.jsx";
import {Button} from "../ui/button.jsx";
import {Textarea} from "../ui/textarea.jsx";
import categories from "../../mockedData/categories.js";
import {Select, SelectContent, SelectItem, SelectTrigger, SelectValue} from "../ui/select.jsx";

const formSchema = z.object({
    title: z.string(),
    year: z.string().refine(value => {
        return /^\d{4}$/.test(value);
    }, {message: "Year must be 4 digits"}),
    description: z.string(),
    genre: z.string(),
});

const MovieForm = ({movieInfo}) => {

    console.log("movieInfo", movieInfo)

    const form = useForm({
        resolver: zodResolver(formSchema),
        mode: "onSubmit",
        defaultValues: {
            title: movieInfo?.name || "",
            year: movieInfo?.year || "",
            description: movieInfo?.description || "",
            category: movieInfo?.category || "",
        }
    });

    const onSubmit = (data) => {
        console.log("sending data to edit movie id: " + movieInfo.id);
        console.log("data", data);
    }

    return (
        <Form {...form}>
            <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-8">
                <FormField
                    control={form.control}
                    name="title"
                    render={({field}) => (
                        <FormItem>
                            <FormLabel>Title</FormLabel>
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

                <FormField control={form.control} name="genre" render={({field}) => (
                    <FormItem>
                        <FormLabel>Genre</FormLabel>
                        <FormControl>
                            <Select onValueChange={value => {
                                field.onChange(value); // This ensures React Hook Form updates with the new value
                            }} value={field.value}
                            >
                                <SelectTrigger>
                                    <SelectValue>
                                        {categories.find(genre => genre.id === Number(field.value))?.name || "Select a genre"}
                                    </SelectValue>
                                </SelectTrigger>
                                <SelectContent>
                                    {categories.map(genre => (
                                        <SelectItem key={genre.id} value={genre.id}>{genre.name}</SelectItem>
                                    ))}
                                </SelectContent>
                            </Select>
                        </FormControl>
                    </FormItem>
                )}
                />
                <Button type="submit">Submit</Button>
            </form>
        </Form>

    )
        ;
}

export default MovieForm;