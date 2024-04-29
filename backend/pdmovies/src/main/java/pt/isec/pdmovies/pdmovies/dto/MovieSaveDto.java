package pt.isec.pdmovies.pdmovies.dto;

import lombok.AllArgsConstructor;
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
public class MovieSaveDto {
    private String name;
    private Integer year;
    private String description;
    private String banner;
    private Category category;
    private Set<UUID> persons = new HashSet<>();
}
