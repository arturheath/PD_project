import {ScrollArea} from "../ui/scroll-area.jsx";
import {Card, CardDescription, CardHeader, CardTitle} from "../ui/card.jsx";

const CardsContainer = ({movies, onMovieClick}) => {

    // dúvida se não é melhor uma datatable com filtros e sorting
    return (
        <ScrollArea className="h-screen rounded-md border p-4">
            {
                movies.map(movie => (
                    <Card className='m-2 bg-gray-200 cursor-pointer' onClick={() => onMovieClick(movie.id)}>
                        <CardHeader>
                            <CardTitle>{movie.title}</CardTitle>
                            <CardDescription>{movie.year}</CardDescription>
                        </CardHeader>
                    </Card>
                ))
            }
        </ScrollArea>
    );
}

export default CardsContainer;