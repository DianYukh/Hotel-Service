package diana.com.hotel.service;

import diana.com.hotel.dto.BookingRequestDTO;
import diana.com.hotel.entity.Booking;
import diana.com.hotel.entity.EBookingStatus;
import diana.com.hotel.exception.BookingInvalidException;
import diana.com.hotel.exception.HotelNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface BookingService {
    Booking createBooking(BookingRequestDTO bookingRequestDTO) throws BookingInvalidException, HotelNotFoundException;
    List<Booking> getAllBookings();
    Booking getBookingById(Long id);
    void deleteBooking(Long id);
    List<Booking> getBookingsByStatus(EBookingStatus status);
    List<Booking> getActiveBookingsForToday();
    Booking updateBooking(Long id, BookingRequestDTO bookingRequestDTO) throws BookingInvalidException, HotelNotFoundException;
    Booking updateBookingStatus(Long id);
}
