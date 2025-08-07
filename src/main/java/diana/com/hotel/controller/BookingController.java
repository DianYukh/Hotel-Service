package diana.com.hotel.controller;

import diana.com.hotel.dto.BookingRequestDTO;
import diana.com.hotel.entity.Booking;

import diana.com.hotel.entity.EBookingStatus;
import diana.com.hotel.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public Booking createBooking(@RequestBody BookingRequestDTO bookingRequestDTO) {
        return bookingService.createBooking(bookingRequestDTO);
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
    }

    @PutMapping("/{id}")
    public Booking updateBooking(@PathVariable Long id, @RequestBody BookingRequestDTO bookingRequestDTO) {
        return bookingService.updateBooking(id, bookingRequestDTO);
    }

    @GetMapping("/status")
    public List<Booking> getBookingsByStatus(@RequestParam EBookingStatus status) {
        return bookingService.getBookingsByStatus(status);
    }

    @PatchMapping("/{id}")
    public Booking updateBookingStatus(@PathVariable Long id) {
        return bookingService.updateBookingStatus(id);
    }

    @GetMapping("/active/today")
    public List<Booking> getActiveBookingsForToday() {
        return bookingService.getActiveBookingsForToday();
    }
}
