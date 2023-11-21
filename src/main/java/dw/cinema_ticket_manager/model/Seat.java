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
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Column(name = "seat_number")
    private String seatNumber;
    private boolean available;

    public Seat() {}

    public Seat(Room room, String seatNumber, boolean available) {
        this.room = room;
        this.seatNumber = seatNumber;
        this.available = available;
    }

    public Seat(Room room, String seatNumber) {
        this.room = room;
        this.seatNumber = seatNumber;
        this.available = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return Objects.equals(room, seat.room) && Objects.equals(seatNumber, seat.seatNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(room, seatNumber);
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", room=" + room +
                ", seatNumber='" + seatNumber + '\'' +
                ", available=" + available +
                '}';
    }
}
