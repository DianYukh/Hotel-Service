package diana.com.hotel.service;

import diana.com.hotel.dto.RoomRequestDTO;
import diana.com.hotel.entity.ERoomType;
import diana.com.hotel.entity.Room;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface RoomService {
    List<Room> getAllRooms();
    Room getRoomById(Long id);
    void deleteRoom(Long id);
    Room createRoom(RoomRequestDTO roomRequestDTO);
    Room updateRoom(Long id, RoomRequestDTO roomRequestDTO);
    List<Room> getRoomByType(ERoomType type);
    Room updateRoomType(Long id, ERoomType type);
}
