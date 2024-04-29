package pt.isec.pdmovies.pdmovies.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pt.isec.pdmovies.pdmovies.enums.Role;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CelebritySaveDto {
    private String firstName;
    private String lastName;
    private String photo;
    private Date birthday;
    private Role role;
    private Set<UUID> movies = new HashSet<>();
}
