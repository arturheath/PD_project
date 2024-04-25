package pt.isec.pdmovies.pdmovies.converter;

import org.springframework.beans.BeanUtils;
import pt.isec.pdmovies.pdmovies.dto.CelebrityDto;
import pt.isec.pdmovies.pdmovies.dto.CelebritySaveDto;
import pt.isec.pdmovies.pdmovies.dto.MovieDto;
import pt.isec.pdmovies.pdmovies.model.Celebrity;
import pt.isec.pdmovies.pdmovies.model.Movie;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CelebrityConverter {

    public static CelebrityDto celebrityToCelebrityDto(Celebrity celebrity) {
        CelebrityDto dto = new CelebrityDto();
        BeanUtils.copyProperties(celebrity, dto);
        Set<MovieDto> movieDtos = celebrity.getMovies().stream()
                .map(m -> {
                    MovieDto movieDto = new MovieDto();
                    BeanUtils.copyProperties(m, movieDto);
                    return movieDto;
                })
                .collect(Collectors.toSet());
        dto.setMovies(movieDtos);
        return dto;
    }

    public static Celebrity celebritySaveDtoToCelebrity(CelebritySaveDto dto, List<Movie> movies) {
        Celebrity celebrity = new Celebrity();
        BeanUtils.copyProperties(dto, celebrity, "movies");
        celebrity.getMovies().addAll(movies);
        return celebrity;
    }

}
