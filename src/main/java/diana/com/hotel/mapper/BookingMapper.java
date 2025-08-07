package diana.com.hotel.mapper;

import diana.com.hotel.dto.BookingRequestDTO;
import diana.com.hotel.entity.Booking;
import diana.com.hotel.entity.EBookingStatus;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {
    public Booking mapBookingRequestDTOToBookingEntity(BookingRequestDTO bookingRequestDTO){
        return Booking.builder()
                .checkInDate(bookingRequestDTO.getCheckInDate())
                .checkOutDate(bookingRequestDTO.getCheckOutDate())
                .stars(bookingRequestDTO.getStars())
                .status(EBookingStatus.NEW)
                .build();
    }
}
