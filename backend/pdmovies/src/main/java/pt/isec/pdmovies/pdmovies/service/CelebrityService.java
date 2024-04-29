package pt.isec.pdmovies.pdmovies.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.isec.pdmovies.pdmovies.converter.CelebrityConverter;
import pt.isec.pdmovies.pdmovies.dto.CelebrityDto;
import pt.isec.pdmovies.pdmovies.dto.CelebritySaveDto;
import pt.isec.pdmovies.pdmovies.model.Celebrity;
import pt.isec.pdmovies.pdmovies.model.Movie;
import pt.isec.pdmovies.pdmovies.repository.CelebrityRepository;
import pt.isec.pdmovies.pdmovies.repository.MovieRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CelebrityService {
    private final CelebrityRepository celebrityRepository;
    private final MovieRepository movieRepository;

    @Transactional
    public CelebrityDto createCelebrity(CelebritySaveDto dto) {
        List<Movie> movies = findMoviesOrThrow(dto.getMovies());
        Celebrity celebrity = CelebrityConverter.celebritySaveDtoToCelebrity(dto, movies);
        celebrity = celebrityRepository.save(celebrity);
        return CelebrityConverter.celebrityToCelebrityDto(celebrity);
    }

    private List<Movie> findMoviesOrThrow(Set<UUID> movieIds) {
        List<Movie> movies = movieRepository.findAllById(movieIds);
        if (movieIds.size() > movies.size()) {
            throw new IllegalArgumentException("Movies not found");
        }
        return movies;
    }

    @Transactional(readOnly = true)
    public List<CelebrityDto> getAllCelebrities() {
        return celebrityRepository.findAll().stream()
                .map(CelebrityConverter::celebrityToCelebrityDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CelebrityDto getCelebrity(UUID id) {
        Celebrity celebrity = celebrityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Celebrity not found"));
        return CelebrityConverter.celebrityToCelebrityDto(celebrity);
    }

    @Transactional
    public CelebrityDto updateCelebrity(UUID id, CelebritySaveDto dto) {
        Celebrity celebrity = celebrityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Celebrity not found"));
        Set<UUID> movieIds = dto.getMovies();
        List<Movie> movies = movieRepository.findAllById(movieIds);
        if (movieIds.containsAll(movies.stream().map(Movie::getId).collect(Collectors.toSet()))) {
            throw new IllegalArgumentException("Movies with not found");
        }
        celebrity = CelebrityConverter.celebritySaveDtoToCelebrity(dto, movies);
        celebrity = celebrityRepository.save(celebrity);
        return CelebrityConverter.celebrityToCelebrityDto(celebrity);
    }

    @Transactional
    public void deleteCelebrity(UUID id) {
        celebrityRepository.deleteById(id);
    }
}
