package dw.cinema_ticket_manager.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomFactory {
    private Map<Integer, String> roomTemplates;
    public RoomFactory() {
        this.roomTemplates = new HashMap<>();
        roomTemplates.put(1, "8,16");
        roomTemplates.put(2, "11,15");
    }

    public Room getRoomCopy(int roomNumber) {
        return createRoom(roomNumber);
    }

    private Room createRoom(int roomNumber) {
        var rowsAndColumns = roomTemplates.get(roomNumber);
        int commaIndex = rowsAndColumns.indexOf(",");
        int rows = Integer.parseInt(rowsAndColumns.substring(0, commaIndex));
        int columns = Integer.parseInt(rowsAndColumns.substring(commaIndex + 1));

        var room = new Room(roomNumber, rows, columns);
        createSeats(room);
        return room;
    }

    private void createSeats(Room room) {
        var seats = new ArrayList<Seat>();

        for (int r = 1; r <= room.getRows(); r++) {
            for (int c = 1; c <= room.getColumns(); c++) {
                var seat = new Seat(room, r, c);
                seats.add(seat);
            }
        }
        room.setSeats(seats);
    }
}
