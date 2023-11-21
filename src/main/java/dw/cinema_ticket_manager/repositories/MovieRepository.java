package dw.cinema_ticket_manager.repositories;

import dw.cinema_ticket_manager.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByGenresName(String genreName);
    List<Movie> findByReleaseYear(int releaseYear);
}
