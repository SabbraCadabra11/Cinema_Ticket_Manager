package dw.cinema_ticket_manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "showtimes")
public class Showtime implements Comparable<Showtime> {
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

    public String getShortenedPlFormattedDate() {
        return switch (eventDate.getMonthValue()) {
            case 1 -> "%d %s %d".formatted(eventDate.getDayOfMonth(), "sty. ", eventDate.getYear());
            case 2 -> "%d %s %d".formatted(eventDate.getDayOfMonth(), "lut. ", eventDate.getYear());
            case 3 -> "%d %s %d".formatted(eventDate.getDayOfMonth(), "mar. ", eventDate.getYear());
            case 4 -> "%d %s %d".formatted(eventDate.getDayOfMonth(), "kwi. ", eventDate.getYear());
            case 5 -> "%d %s %d".formatted(eventDate.getDayOfMonth(), "maja ", eventDate.getYear());
            case 6 -> "%d %s %d".formatted(eventDate.getDayOfMonth(), "cze. ", eventDate.getYear());
            case 7 -> "%d %s %d".formatted(eventDate.getDayOfMonth(), "lip. ", eventDate.getYear());
            case 8 -> "%d %s %d".formatted(eventDate.getDayOfMonth(), "sie. ", eventDate.getYear());
            case 9 -> "%d %s %d".formatted(eventDate.getDayOfMonth(), "wrz. ", eventDate.getYear());
            case 10 -> "%d %s %d".formatted(eventDate.getDayOfMonth(), "paź. ", eventDate.getYear());
            case 11 -> "%d %s %d".formatted(eventDate.getDayOfMonth(), "lis. ", eventDate.getYear());
            case 12 -> "%d %s %d".formatted(eventDate.getDayOfMonth(), "gru. ", eventDate.getYear());
            default -> "";
        };
    }

    public String getPlFormattedDate() {
        var formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("pl"));
        return eventDate.format(formatter);
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

    @Override
    public int compareTo(Showtime o) {
        return this.getEventTime().compareTo(o.getEventTime());
    }
}
