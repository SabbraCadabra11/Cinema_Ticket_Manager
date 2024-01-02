package dw.cinema_ticket_manager.services;

import dw.cinema_ticket_manager.model.Showtime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface ShowtimeService {
    void addShowtime(Showtime showtime);
    List<Showtime> getAllShowtimes();
    List<Showtime> getShowtimesByDate(LocalDate date);
    Showtime getShowtimesByDateAndTime(LocalDate date, LocalTime time);
    Showtime getShowtimeById(UUID id);
}
