package diana.com.hotel.repository;


import diana.com.hotel.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>  {
    Optional<Hotel> findHotelById(Long id);

}
