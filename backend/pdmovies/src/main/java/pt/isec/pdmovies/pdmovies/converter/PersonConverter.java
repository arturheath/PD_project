package pt.isec.pdmovies.pdmovies.converter;

import org.springframework.beans.BeanUtils;
import pt.isec.pdmovies.pdmovies.dto.PersonDto;
import pt.isec.pdmovies.pdmovies.dto.PersonSaveDto;
import pt.isec.pdmovies.pdmovies.dto.MovieDto;
import pt.isec.pdmovies.pdmovies.model.Person;
import pt.isec.pdmovies.pdmovies.model.Movie;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PersonConverter {

    public static PersonDto personToPersonDto(Person person) {
        PersonDto dto = new PersonDto();
        BeanUtils.copyProperties(person, dto);
        Set<MovieDto> movieDtos = person.getMovies().stream()
                .map(m -> {
                    MovieDto movieDto = new MovieDto();
                    BeanUtils.copyProperties(m, movieDto);
                    return movieDto;
                })
                .collect(Collectors.toSet());
        dto.setMovies(movieDtos);
        return dto;
    }

    public static Person personSaveDtoToPerson(PersonSaveDto dto, List<Movie> movies) {
        Person person = new Person();
        BeanUtils.copyProperties(dto, person, "movies");
        person.getMovies().addAll(movies);
        return person;
    }

}
