package dw.cinema_ticket_manager.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

@Getter
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private long id;
    @OneToMany(mappedBy = "room")
    private List<Showtime> showtimes;
    @Column(name = "room_number")
    private int roomNumber;

    public Room() {}

    public Room(List<Showtime> showtimes, int roomNumber) {
        this.showtimes = showtimes;
        this.roomNumber = roomNumber;
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
