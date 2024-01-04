package dw.cinema_ticket_manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private long id;
    @OneToMany(mappedBy = "room")
    private List<Showtime> showtimes;
    @OneToMany(mappedBy = "room")
    private List<Seat> seats;
    @Column(name = "room_number")
    private int roomNumber;
    private int rows;
    private int columns;

    public Room() {}

    public Room(int roomNumber, int rows, int columns) {
        this.roomNumber = roomNumber;
        this.rows = rows;
        this.columns = columns;
    }

    public Room(List<Seat> seats, int roomNumber, int rows, int columns) {
        this.showtimes = new ArrayList<>();
        this.seats = seats;
        this.roomNumber = roomNumber;
        this.rows = rows;
        this.columns = columns;

        for (var seat : seats) {
            seat.setRoom(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomNumber == room.roomNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", showtimes=" + showtimes +
                ", roomNumber=" + roomNumber +
                '}';
    }
}
