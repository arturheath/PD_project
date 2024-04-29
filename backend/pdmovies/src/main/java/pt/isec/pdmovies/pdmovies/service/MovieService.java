package pt.isec.pdmovies.pdmovies.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pt.isec.pdmovies.pdmovies.converter.PersonConverter;
import pt.isec.pdmovies.pdmovies.converter.MovieConverter;
import pt.isec.pdmovies.pdmovies.dto.PersonDto;
import pt.isec.pdmovies.pdmovies.dto.PersonSaveDto;
import pt.isec.pdmovies.pdmovies.dto.MovieDto;
import pt.isec.pdmovies.pdmovies.dto.MovieSaveDto;
import pt.isec.pdmovies.pdmovies.enums.Role;
import pt.isec.pdmovies.pdmovies.model.Person;
import pt.isec.pdmovies.pdmovies.model.Movie;
import pt.isec.pdmovies.pdmovies.repository.PersonRepository;
import pt.isec.pdmovies.pdmovies.repository.MovieRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final PersonRepository personRepository;

    @Transactional
    public MovieDto createMovie(MovieSaveDto movieSaveDto) {
        List<Person> persons = getPersonsOrThrow(movieSaveDto.getPersons());
        Movie movie = MovieConverter.movieSaveDtoToMovie(movieSaveDto, persons);
        movie = movieRepository.save(movie);
        return MovieConverter.movieToMovieDto(movie);
    }

    @Transactional
    public List<MovieDto> getAllMovies() {
        return movieRepository.findAll().stream()
                .map(MovieConverter::movieToMovieDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public MovieDto getMovieById(UUID id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found"));
        return MovieConverter.movieToMovieDto(movie);
    }

    @Transactional
    public MovieDto updateMovie(UUID id, MovieSaveDto movieSaveDto) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found"));
        List<Person> persons = getPersonsOrThrow(movieSaveDto.getPersons());
        movie = MovieConverter.movieSaveDtoToMovie(movieSaveDto, persons);
        movie.setId(id);
        movie = movieRepository.save(movie);
        return MovieConverter.movieToMovieDto(movie);
    }

    @Transactional
    public void deleteMovie(UUID id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found"));
        movieRepository.delete(movie);
    }

    @Transactional
    public MovieDto getMovieWithFilteredPersons(UUID movieId, Role role) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found"));

        Set<Person> persons =
                role == null
                        ? movie.getPersons()
                        : movie.getPersons().stream()
                        .filter(person -> person.getRole() != null && person.getRole().equals(role))
                        .collect(Collectors.toSet());
        Set<PersonDto> personDtos = persons.stream()
                .map(PersonConverter::personToPersonDto)
                .collect(Collectors.toSet());

        MovieDto dto = MovieConverter.movieToMovieDto(movie);
        dto.getPersons().retainAll(personDtos);
        return dto;
    }

    @Transactional
    public MovieDto addPersonsToMovie(UUID movieId, List<PersonSaveDto> personDtos) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Set<Person> persons = personDtos.stream()
                .map(dto -> PersonConverter.personSaveDtoToPerson(dto, singletonList(movie)))
                .collect(Collectors.toSet());

        movie.getPersons().addAll(persons);

        Movie updatedMovie = movieRepository.save(movie);
        return MovieConverter.movieToMovieDto(updatedMovie);
    }

    private List<Person> getPersonsOrThrow(Set<UUID> personIds) {
        List<Person> persons = personRepository.findAllById(personIds);
        if (personIds.size() > persons.size()) {
            throw new IllegalArgumentException("Celebs not found");
        }
        return persons;
    }
}
