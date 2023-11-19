package dw.cinema_ticket_manager.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Objects;

@Getter
@Entity
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "showtime_id", nullable = false)
    private Showtime showtime;

    @Column(name = "seat_number")
    private String seatNumber;

    private boolean available;

    public Seat() {}

    public Seat(Showtime showtime, String seatNumber, boolean available) {
        this.showtime = showtime;
        this.seatNumber = seatNumber;
        this.available = available;
    }

    public Seat(Showtime showtime, String seatNumber) {
        this.showtime = showtime;
        this.seatNumber = seatNumber;
        this.available = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return Objects.equals(showtime, seat.showtime) && Objects.equals(seatNumber, seat.seatNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(showtime, seatNumber);
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", showtime=" + showtime +
                ", seatNumber='" + seatNumber + '\'' +
                ", available=" + available +
                '}';
    }
}
