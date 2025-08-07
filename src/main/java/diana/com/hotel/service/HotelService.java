package diana.com.hotel.service;

import diana.com.hotel.dto.HotelRequestDTO;
import diana.com.hotel.entity.Hotel;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface HotelService {
    Hotel createHotel(HotelRequestDTO hotelRequestDTO);
    Hotel getHotelById(Long id);
    Hotel updateHotel(Long id, HotelRequestDTO hotelRequestDTO);
    void deleteHotel(Long id);
    List<Hotel> getAllHotels();
}
