package dw.cinema_ticket_manager.repositories;

import dw.cinema_ticket_manager.model.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
    List<Showtime> findAllByEventDate(LocalDate eventDate);
    Showtime findByEventDateAndEventTime(LocalDate eventDate, LocalTime eventTime);
    void deleteById(long id);
}
