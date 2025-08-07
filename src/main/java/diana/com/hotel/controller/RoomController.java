package diana.com.hotel.controller;


import diana.com.hotel.dto.RoomRequestDTO;
import diana.com.hotel.entity.ERoomType;
import diana.com.hotel.entity.Room;
import diana.com.hotel.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    @GetMapping
    public List<Room> getAllRooms(){
        return roomService.getAllRooms();
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable Long id){
        return roomService.getRoomById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }

    @PostMapping
    public Room createRoom(@RequestBody RoomRequestDTO roomRequestDTO) {
        return roomService.createRoom(roomRequestDTO);
    }

    @PutMapping("/{id}")
    public Room updateRoom(@PathVariable Long id, @RequestBody RoomRequestDTO roomRequestDTO) {
        return roomService.updateRoom(id, roomRequestDTO);
    }

    @GetMapping("/type")
    public List<Room> getRoomByType(@RequestParam ERoomType type) {
        return roomService.getRoomByType(type);
    }

    @PatchMapping("/{id}/updateType")
    public Room updateRoomType(@PathVariable Long id, @RequestParam ERoomType type){
        return roomService.updateRoomType(id, type);
    }
}
