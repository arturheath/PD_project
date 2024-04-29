package pt.isec.pdmovies.pdmovies.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.isec.pdmovies.pdmovies.converter.PersonConverter;
import pt.isec.pdmovies.pdmovies.dto.PersonDto;
import pt.isec.pdmovies.pdmovies.dto.PersonSaveDto;
import pt.isec.pdmovies.pdmovies.model.Person;
import pt.isec.pdmovies.pdmovies.model.Movie;
import pt.isec.pdmovies.pdmovies.repository.PersonRepository;
import pt.isec.pdmovies.pdmovies.repository.MovieRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final MovieRepository movieRepository;

    @Transactional
    public PersonDto createPerson(PersonSaveDto dto) {
        List<Movie> movies = findMoviesOrThrow(dto.getMovies());
        Person person = PersonConverter.personSaveDtoToPerson(dto, movies);
        person = personRepository.save(person);
        return PersonConverter.personToPersonDto(person);
    }

    private List<Movie> findMoviesOrThrow(Set<UUID> movieIds) {
        List<Movie> movies = movieRepository.findAllById(movieIds);
        if (movieIds.size() > movies.size()) {
            throw new IllegalArgumentException("Movies not found");
        }
        return movies;
    }

    @Transactional(readOnly = true)
    public List<PersonDto> getAllPersons() {
        return personRepository.findAll().stream()
                .map(PersonConverter::personToPersonDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PersonDto getPerson(UUID id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found"));
        return PersonConverter.personToPersonDto(person);
    }

    @Transactional
    public PersonDto updatePerson(UUID id, PersonSaveDto dto) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found"));
        Set<UUID> movieIds = dto.getMovies();
        List<Movie> movies = movieRepository.findAllById(movieIds);
        if (movieIds.containsAll(movies.stream().map(Movie::getId).collect(Collectors.toSet()))) {
            throw new IllegalArgumentException("Movies with not found");
        }
        person = PersonConverter.personSaveDtoToPerson(dto, movies);
        person = personRepository.save(person);
        return PersonConverter.personToPersonDto(person);
    }

    @Transactional
    public void deletePerson(UUID id) {
        personRepository.deleteById(id);
    }
}
