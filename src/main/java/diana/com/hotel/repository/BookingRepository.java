package diana.com.hotel.repository;

import diana.com.hotel.entity.Booking;
import diana.com.hotel.entity.EBookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {
    @Query("SELECT b FROM Booking b WHERE b.checkInDate <= :today AND b.checkOutDate > :today")
    List<Booking> findActiveBookingsForDate(@Param("today") LocalDate today);
    List<Booking> findAllByStatus(EBookingStatus status);

}

