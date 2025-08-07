package diana.com.hotel.mapper;

import diana.com.hotel.dto.HotelRequestDTO;
import diana.com.hotel.entity.Hotel;
import org.springframework.stereotype.Component;

@Component
public class HotelMapper {
    public Hotel mapHotelRequestDTOToHotelEntity(HotelRequestDTO hotelRequestDTO) {
        return Hotel.builder()
                .name(hotelRequestDTO.getName())
                .location(hotelRequestDTO.getLocation())
                .build();
    }


}
