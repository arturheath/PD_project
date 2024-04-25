package pt.isec.pdmovies.pdmovies.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pt.isec.pdmovies.pdmovies.enums.Category;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class MovieDto {
    UUID id;
    String name;
    Integer year;
    String description;
    String banner;
    Category category;
    Set<CelebrityDto> celebrities = new HashSet<>();
}
