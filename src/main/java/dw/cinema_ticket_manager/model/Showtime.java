package dw.cinema_ticket_manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
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

    @Column(name = "event_datetime")
    private LocalDateTime eventDateTime;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    public Showtime() {}

    public Showtime(LocalDateTime eventDateTime, Movie movie, Room room) {
        this.eventDateTime = eventDateTime;
        this.movie = movie;
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Showtime showtime = (Showtime) o;
        return room == showtime.room && Objects.equals(eventDateTime, showtime.eventDateTime)
                && Objects.equals(movie, showtime.movie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventDateTime, movie, room);
    }

    @Override
    public String toString() {
        return "Showtime{" +
                "id=" + id +
                ", eventDateTime=" + eventDateTime +
                ", movie=" + movie +
                ", room=" + room +
                '}';
    }
}
