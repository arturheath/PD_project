package pt.isec.pdmovies.pdmovies.converter;

import org.springframework.beans.BeanUtils;
import pt.isec.pdmovies.pdmovies.dto.PersonDto;
import pt.isec.pdmovies.pdmovies.dto.MovieDto;
import pt.isec.pdmovies.pdmovies.dto.MovieSaveDto;
import pt.isec.pdmovies.pdmovies.model.Person;
import pt.isec.pdmovies.pdmovies.model.Movie;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MovieConverter {

    public static Movie movieSaveDtoToMovie(MovieSaveDto dto, List<Person> persons) {
        Movie movie = new Movie();
        BeanUtils.copyProperties(dto, movie, "personIds");
        movie.getPersons().addAll(persons);
        return movie;
    }

    public static MovieDto movieToMovieDto(Movie movie) {
        MovieDto dto = new MovieDto();
        BeanUtils.copyProperties(movie, dto);
        Set<PersonDto> personDtos = movie.getPersons().stream()
                .map(c -> {
                    PersonDto personDto = new PersonDto();
                    BeanUtils.copyProperties(c, personDto);
                    return personDto;
                })
                .collect(Collectors.toSet());
        dto.setPersons(personDtos);
        return dto;
    }

}
