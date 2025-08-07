package diana.com.hotel.service.impl;

import diana.com.hotel.entity.Hotel;
import diana.com.hotel.entity.Room;
import diana.com.hotel.dto.BookingRequestDTO;
import diana.com.hotel.entity.Booking;
import diana.com.hotel.entity.EBookingStatus;
import diana.com.hotel.exception.BookingInvalidException;
import diana.com.hotel.exception.HotelNotFoundException;
import diana.com.hotel.exception.RoomNotFoundException;
import diana.com.hotel.mapper.BookingMapper;
import diana.com.hotel.repository.BookingRepository;
import diana.com.hotel.repository.HotelRepository;
import diana.com.hotel.repository.RoomRepository;
import diana.com.hotel.service.BookingService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;

    @Override
    public Booking createBooking(BookingRequestDTO bookingRequestDTO) throws BookingInvalidException, HotelNotFoundException {
        if (bookingRequestDTO.getCheckOutDate().before(bookingRequestDTO.getCheckInDate())) {
            throw new BookingInvalidException("Check in date must come before check out date");
        }

        Hotel hotel = hotelRepository.findHotelById(bookingRequestDTO.getHotelId())
                .orElseThrow(() -> new HotelNotFoundException("Hotel not found"));

        List<Room> rooms = roomRepository.findAllById(bookingRequestDTO.getRoomsIds());

        if (rooms.size() != bookingRequestDTO.getRoomsIds().size()) {
            throw new RoomNotFoundException("One or more rooms not found");
        }

        Booking booking = bookingMapper.mapBookingRequestDTOToBookingEntity(bookingRequestDTO);
        booking.setHotel(hotel);
        booking.setStatus(EBookingStatus.NEW);

        for (Room room : rooms) {
            room.setBooking(booking);
        }

        booking.setRooms(rooms);

        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(Long id, BookingRequestDTO bookingRequestDTO) throws BookingInvalidException, HotelNotFoundException {
        Booking booking = getBookingById(id);
        updateBookingEntity(bookingRequestDTO, booking);
        if (bookingRequestDTO.getCheckOutDate().before(bookingRequestDTO.getCheckInDate())) {
            throw new BookingInvalidException("Check in date must come before check out date");
        }
        Hotel hotel = hotelRepository.findHotelById(bookingRequestDTO.getHotelId())
                .orElseThrow(() -> new HotelNotFoundException("Hotel not found"));
        booking.setHotel(hotel);
        List<Room> rooms = roomRepository.findAllById(bookingRequestDTO.getRoomsIds());
        if (rooms.size() != bookingRequestDTO.getRoomsIds().size()) {
            throw new RoomNotFoundException("One or more rooms not found");
        }

        if (booking.getRooms() != null) {
            for (Room room : booking.getRooms()) {
                room.setBooking(null);
            }
        }

        for (Room room : rooms) {
            room.setBooking(booking);
        }
        booking.setRooms(rooms);


        booking.setStatus(EBookingStatus.UPDATED);
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingsByStatus(EBookingStatus status) {
        return bookingRepository.findAllByStatus(status);
    }

    @Override
    public Booking updateBookingStatus(Long id) {
        Booking booking = getBookingById(id);
        booking.setStatus(EBookingStatus.CONFIRMED);
        booking.setStatus(EBookingStatus.CANCELLED);
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getActiveBookingsForToday() {
        return bookingRepository.findActiveBookingsForDate(LocalDate.now());
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Can not found a hotel with id: %s".formatted(id)));
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    private void updateBookingEntity(BookingRequestDTO bookingRequestDTO, Booking booking) {
        booking.setCheckInDate(bookingRequestDTO.getCheckInDate());
        booking.setCheckOutDate(bookingRequestDTO.getCheckOutDate());
        booking.setStars(bookingRequestDTO.getStars());
        booking.setRooms(roomRepository.findAll());
    }
}
