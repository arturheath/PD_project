package pt.isec.pdmovies.pdmovies.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pt.isec.pdmovies.pdmovies.converter.CelebrityConverter;
import pt.isec.pdmovies.pdmovies.converter.MovieConverter;
import pt.isec.pdmovies.pdmovies.dto.CelebrityDto;
import pt.isec.pdmovies.pdmovies.dto.CelebritySaveDto;
import pt.isec.pdmovies.pdmovies.dto.MovieDto;
import pt.isec.pdmovies.pdmovies.dto.MovieSaveDto;
import pt.isec.pdmovies.pdmovies.enums.Role;
import pt.isec.pdmovies.pdmovies.model.Celebrity;
import pt.isec.pdmovies.pdmovies.model.Movie;
import pt.isec.pdmovies.pdmovies.repository.CelebrityRepository;
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
    private final CelebrityRepository celebrityRepository;

    @Transactional
    public MovieDto createMovie(MovieSaveDto movieSaveDto) {
        List<Celebrity> celebrities = getCelebritiesOrThrow(movieSaveDto.getCelebrities());
        Movie movie = MovieConverter.movieSaveDtoToMovie(movieSaveDto, celebrities);
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
        List<Celebrity> celebrities = getCelebritiesOrThrow(movieSaveDto.getCelebrities());
        movie = MovieConverter.movieSaveDtoToMovie(movieSaveDto, celebrities);
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
    public MovieDto getMovieWithFilteredCelebrities(UUID movieId, Role role) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found"));

        Set<Celebrity> celebrities =
                role == null
                        ? movie.getCelebrities()
                        : movie.getCelebrities().stream()
                        .filter(celebrity -> celebrity.getRole() != null && celebrity.getRole().equals(role))
                        .collect(Collectors.toSet());
        Set<CelebrityDto> celebrityDtos = celebrities.stream()
                .map(CelebrityConverter::celebrityToCelebrityDto)
                .collect(Collectors.toSet());

        MovieDto dto = MovieConverter.movieToMovieDto(movie);
        dto.getCelebrities().retainAll(celebrityDtos);
        return dto;
    }

    @Transactional
    public MovieDto addCelebritiesToMovie(UUID movieId, List<CelebritySaveDto> celebrityDtos) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Set<Celebrity> celebrities = celebrityDtos.stream()
                .map(dto -> CelebrityConverter.celebritySaveDtoToCelebrity(dto, singletonList(movie)))
                .collect(Collectors.toSet());

        movie.getCelebrities().addAll(celebrities);

        Movie updatedMovie = movieRepository.save(movie);
        return MovieConverter.movieToMovieDto(updatedMovie);
    }

    private List<Celebrity> getCelebritiesOrThrow(Set<UUID> celebrityIds) {
        List<Celebrity> celebrities = celebrityRepository.findAllById(celebrityIds);
        if (celebrityIds.size() > celebrities.size()) {
            throw new IllegalArgumentException("Celebs not found");
        }
        return celebrities;
    }
}
