package pt.isec.pdmovies.pdmovies.converter;

import org.springframework.beans.BeanUtils;
import pt.isec.pdmovies.pdmovies.dto.CelebrityDto;
import pt.isec.pdmovies.pdmovies.dto.MovieDto;
import pt.isec.pdmovies.pdmovies.dto.MovieSaveDto;
import pt.isec.pdmovies.pdmovies.model.Celebrity;
import pt.isec.pdmovies.pdmovies.model.Movie;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MovieConverter {

    public static Movie movieSaveDtoToMovie(MovieSaveDto dto, List<Celebrity> celebrities) {
        Movie movie = new Movie();
        BeanUtils.copyProperties(dto, movie, "celebrityIds");
        movie.getCelebrities().addAll(celebrities);
        return movie;
    }

    public static MovieDto movieToMovieDto(Movie movie) {
        MovieDto dto = new MovieDto();
        BeanUtils.copyProperties(movie, dto);
        Set<CelebrityDto> celebrityDtos = movie.getCelebrities().stream()
                .map(c -> {
                    CelebrityDto celebrityDto = new CelebrityDto();
                    BeanUtils.copyProperties(c, celebrityDto);
                    return celebrityDto;
                })
                .collect(Collectors.toSet());
        dto.setCelebrities(celebrityDtos);
        return dto;
    }

}
