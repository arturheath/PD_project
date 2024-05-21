package pt.isec.pdmovies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import pt.isec.pdmovies.pdmovies.controller.MovieController;
import pt.isec.pdmovies.pdmovies.dto.MovieDto;
import pt.isec.pdmovies.pdmovies.service.MovieService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class MovieControllerTest {

    @InjectMocks
    private MovieController movieController;

    @Mock
    private MovieService movieService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllMovies() {
        // Arrange
        List<MovieDto> mockMovies = new ArrayList<>();
        mockMovies.add(new MovieDto());  // Add necessary details to the DTO if needed
        when(movieService.getAllMovies()).thenReturn(mockMovies);

        // Act
        ResponseEntity<List<MovieDto>> response = movieController.getAllMovies();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockMovies, response.getBody());
    }
}
