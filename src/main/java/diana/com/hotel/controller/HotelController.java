package diana.com.hotel.controller;

import diana.com.hotel.dto.HotelRequestDTO;
import diana.com.hotel.entity.Hotel;
import diana.com.hotel.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/hotels")
public class HotelController {
    private final HotelService hotelService;

    @PostMapping
    public Hotel create(@RequestBody HotelRequestDTO hotelRequestDTO) {
        return hotelService.createHotel(hotelRequestDTO);
    }

    @GetMapping
    public List<Hotel> getAll() {
        return hotelService.getAllHotels();
    }

    @GetMapping("/{id}")
    public Hotel getById(@PathVariable Long id) {
        return hotelService.getHotelById(id);
    }

    @PutMapping("/{id}")
    public Hotel update(@PathVariable Long id, @RequestBody HotelRequestDTO hotelRequestDTO) {
        return hotelService.updateHotel(id, hotelRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        hotelService.deleteHotel(id);
    }
}
