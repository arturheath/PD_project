package pt.isec.pdmovies.pdmovies.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.isec.pdmovies.pdmovies.dto.PersonSaveDto;
import pt.isec.pdmovies.pdmovies.dto.MovieDto;
import pt.isec.pdmovies.pdmovies.dto.MovieSaveDto;
import pt.isec.pdmovies.pdmovies.enums.Role;
import pt.isec.pdmovies.pdmovies.service.MovieService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieDto> createMovie(@RequestBody MovieSaveDto movieSaveDto) {
        MovieDto movieDto = movieService.createMovie(movieSaveDto);
        return ResponseEntity.ok(movieDto);
    }

    @GetMapping
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        List<MovieDto> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovie(@PathVariable UUID id) {
        MovieDto movieDto = movieService.getMovieById(id);
        return ResponseEntity.ok(movieDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> updateMovie(@PathVariable UUID id, @RequestBody MovieSaveDto movieSaveDto) {
        MovieDto movieDto = movieService.updateMovie(id, movieSaveDto);
        return ResponseEntity.ok(movieDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable UUID id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{movieId}/people")
    public ResponseEntity<MovieDto> getMovieActors(@PathVariable UUID movieId, @RequestParam(required = false) Role role) {
        return ResponseEntity.ok(movieService.getMovieWithFilteredPersons(movieId, role));
    }

    @PostMapping("/{movieId}/people")
    public ResponseEntity<MovieDto> addPersonsToMovie(@PathVariable UUID movieId, @RequestBody List<PersonSaveDto> personDtos) {
        return ResponseEntity.ok(movieService.addPersonsToMovie(movieId, personDtos));
    }
}
