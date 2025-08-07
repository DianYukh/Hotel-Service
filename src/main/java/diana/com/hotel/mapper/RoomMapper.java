package diana.com.hotel.mapper;

import diana.com.hotel.dto.RoomRequestDTO;
import diana.com.hotel.entity.Room;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {
    public Room mapRoomRequestDTOToRoomEntity(RoomRequestDTO roomRequestDTO){
        return Room.builder()
                .costPerNight(roomRequestDTO.getCostPerNight())
                .type(roomRequestDTO.getType())
                .numberOfGuests(roomRequestDTO.getNumberOfGuests())
                .build();
    }
}
