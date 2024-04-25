package pt.isec.pdmovies.pdmovies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.isec.pdmovies.pdmovies.model.Celebrity;

import java.util.UUID;

@Repository
public interface CelebrityRepository extends JpaRepository<Celebrity, UUID> {}