package dw.cinema_ticket_manager.services.impl;

import dw.cinema_ticket_manager.model.Showtime;
import dw.cinema_ticket_manager.repositories.ShowtimeRepository;
import dw.cinema_ticket_manager.services.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
public class ShowtimeServiceImpl implements ShowtimeService {
    private final ShowtimeRepository showtimeRepository;

    @Autowired
    public ShowtimeServiceImpl(ShowtimeRepository showtimeRepository) {
        this.showtimeRepository = showtimeRepository;
    }

    @Override
    public void addShowtime(Showtime showtime) {
        showtimeRepository.save(showtime);
    }

    @Override
    public List<Showtime> getAllShowtimes() {
        return showtimeRepository.findAll();
    }

    @Override
    public Showtime getShowtimeById(UUID id) {
        return showtimeRepository.findById(id);
    }

    @Override
    public List<Showtime> getShowtimesByDate(LocalDate date) {
        return showtimeRepository.findAllByEventDate(date);
    }

    @Override
    public Showtime getShowtimesByDateAndTime(LocalDate date, LocalTime time) {
        return showtimeRepository.findByEventDateAndEventTime(date, time);
    }
}
