package pt.isec.pdmovies.pdmovies.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;
import pt.isec.pdmovies.pdmovies.enums.Role;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class CelebrityDto {
    UUID id;
    String firstName;
    String lastName;
    String photo;
    Date birthday;
    Role role;
    Set<MovieDto> movies = new HashSet<>();
}
