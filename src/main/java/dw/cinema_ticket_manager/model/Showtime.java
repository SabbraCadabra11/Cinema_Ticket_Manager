package dw.cinema_ticket_manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "showtimes")
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "showtime_id")
    private long id;
    @Column(name = "event_date")
    private LocalDate eventDate;
    @Column(name = "event_time")
    private LocalTime eventTime;
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;
    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    public Showtime() {}

    public Showtime(LocalDate eventDate, LocalTime eventTime, Movie movie, Room room) {
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.movie = movie;
        this.room = room;
    }
}
