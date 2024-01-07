package dw.cinema_ticket_manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
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
    @Column(name = "seat_row")
    private int row;
    @Column(name = "seat_column")
    private int column;
    private boolean available;

    public Seat() {}

    public Seat(Room room, int row, int column) {
        this.room = room;
        this.row = row;
        this.column = column;
        this.available = true;
    }

    public String getRowInRomanNumerals() {
        final int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        final String[] romanLiterals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        final var rowInRoman = new StringBuilder();
        int rowValue = row;

        for (int i = 0; i < values.length; i++) {
            while (rowValue >= values[i]) {
                rowValue -= values[i];
                rowInRoman.append(romanLiterals[i]);
            }
        }
        return rowInRoman.toString();
    }

    public void setIsAvailable(boolean status) {
        available = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return id == seat.id &&
                row == seat.row &&
                column == seat.column &&
                Objects.equals(room, seat.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, room, row, column);
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", room=" + room +
                ", row=" + row +
                ", column=" + column +
                ", available=" + available +
                '}';
    }
}
