package dw.cinema_ticket_manager.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RoomFactory {
    private Map<Integer, String> roomTemplates;
    public RoomFactory() {
        this.roomTemplates = new HashMap<>();
        roomTemplates.put(1, "6,9");
        roomTemplates.put(2, "7,7");
        roomTemplates.put(3, "8,17");
        roomTemplates.put(4, "10,14");
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
