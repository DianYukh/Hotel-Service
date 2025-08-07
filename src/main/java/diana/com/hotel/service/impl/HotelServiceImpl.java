package diana.com.hotel.service.impl;

import diana.com.hotel.dto.HotelRequestDTO;
import diana.com.hotel.entity.Hotel;
import diana.com.hotel.exception.HotelNotFoundException;
import diana.com.hotel.mapper.HotelMapper;
import diana.com.hotel.repository.HotelRepository;
import diana.com.hotel.service.HotelService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    @Override
    public Hotel createHotel(HotelRequestDTO hotelRequestDTO) {
        Hotel entityHotel = hotelMapper.mapHotelRequestDTOToHotelEntity(hotelRequestDTO);
        return hotelRepository.save(entityHotel);
    }

    @Override
    public Hotel updateHotel(Long id, HotelRequestDTO hotelRequestDTO) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException("Can not found a hotel with id: %s"
                .formatted(id)));
        hotel.setName(hotelRequestDTO.getName());
        hotel.setLocation(hotelRequestDTO.getLocation());
        return hotelRepository.save(hotel);
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException("Can not found a hotel with id: %s"
                        .formatted(id)));
    }

    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }
}
