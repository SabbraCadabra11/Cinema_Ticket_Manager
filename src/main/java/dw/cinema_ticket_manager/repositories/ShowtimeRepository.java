package dw.cinema_ticket_manager.repositories;

import dw.cinema_ticket_manager.model.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
    List<Showtime> findAllByEventDate(LocalDate eventDate);
    Showtime findByEventDateAndEventTime(LocalDate eventDate, LocalTime eventTime);
    Showtime findById(UUID id);
    void deleteById(UUID id);
}
