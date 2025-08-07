package diana.com.hotel.repository;

import diana.com.hotel.entity.ERoomType;
import diana.com.hotel.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findRoomByType(ERoomType type);

}
