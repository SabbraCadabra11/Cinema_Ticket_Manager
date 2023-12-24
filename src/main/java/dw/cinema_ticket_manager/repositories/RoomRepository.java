package dw.cinema_ticket_manager.repositories;

import dw.cinema_ticket_manager.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Room getRoomById(long id);
    Room getRoomByRoomNumber(int roomNumber);
    void deleteByRoomNumber(int roomNumber);
}
