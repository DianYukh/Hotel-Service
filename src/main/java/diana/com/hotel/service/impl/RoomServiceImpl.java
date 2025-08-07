package diana.com.hotel.service.impl;

import diana.com.hotel.dto.RoomRequestDTO;
import diana.com.hotel.entity.ERoomType;
import diana.com.hotel.entity.Room;
import diana.com.hotel.exception.RoomNotFoundException;
import diana.com.hotel.mapper.RoomMapper;
import diana.com.hotel.repository.RoomRepository;
import diana.com.hotel.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElseThrow(() ->
                new RoomNotFoundException("Can not found a room with id: %s".formatted(id)));
    }

    @Override
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public Room createRoom(RoomRequestDTO roomRequestDTO) {
        Room entityRoom = roomMapper.mapRoomRequestDTOToRoomEntity(roomRequestDTO);
        return roomRepository.save(entityRoom);
    }

    @Override
    public Room updateRoom(Long id, RoomRequestDTO roomRequestDTO) {
        Room room = getRoomById(id);
        updateRoomEntity(roomRequestDTO, room);
        return roomRepository.save(room);
    }

    @Override
    public List<Room> getRoomByType(ERoomType type) {
        return roomRepository.findRoomByType(type);
    }

    @Override
    public Room updateRoomType(Long id, ERoomType type){
        Room room = getRoomById(id);
        room.setType(type);
        return roomRepository.save(room);
    }

    private void updateRoomEntity(RoomRequestDTO roomRequestDTO, Room room) {
        room.setCostPerNight(roomRequestDTO.getCostPerNight());
        room.setNumberOfGuests(roomRequestDTO.getNumberOfGuests());
        room.setType(roomRequestDTO.getType());
    }

}
