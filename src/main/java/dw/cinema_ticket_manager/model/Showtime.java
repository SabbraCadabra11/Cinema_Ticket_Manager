package dw.cinema_ticket_manager.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

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
    @Column(name = "room")
    private int room;
}
