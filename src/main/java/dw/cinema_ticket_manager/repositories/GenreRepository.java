package dw.cinema_ticket_manager.repositories;

import dw.cinema_ticket_manager.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findGenreByName(String name);
    Genre findGenreById(Long id);
}
