package pt.isec.pdmovies.pdmovies.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import pt.isec.pdmovies.pdmovies.enums.Role;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "celebrities")
public class Celebrity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String firstName;

    private String lastName;

    private String photo;

    private Date birthday;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private Role role;

    @ManyToMany(mappedBy = "celebrities")
    private Set<Movie> movies = new HashSet<>();
}
