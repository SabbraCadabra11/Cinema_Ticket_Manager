package dw.cinema_ticket_manager.repositories;

import dw.cinema_ticket_manager.model.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {

}
