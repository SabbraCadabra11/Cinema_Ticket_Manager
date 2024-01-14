package dw.cinema_ticket_manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "showtimes")
public class Showtime {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "showtime_id", columnDefinition = "uuid")
    private UUID id;
    @Column(name = "event_date")
    private LocalDate eventDate;
    @Column(name = "event_time")
    private LocalTime eventTime;
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;
    @OneToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;
    @Column(name = "base_price")
    private int basePrice;
    private int roomNumber;

    public Showtime() {}

    public Showtime(LocalDate eventDate, LocalTime eventTime, Movie movie, int roomNumber, int basePrice) {
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.movie = movie;
        this.roomNumber = roomNumber;
        this.basePrice = basePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Showtime showtime = (Showtime) o;
        return Objects.equals(id, showtime.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventDate, eventTime, movie, room);
    }
}
