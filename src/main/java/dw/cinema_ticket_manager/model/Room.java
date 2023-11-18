package dw.cinema_ticket_manager.model;

import jakarta.persistence.*;

import java.util.List;

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
}
