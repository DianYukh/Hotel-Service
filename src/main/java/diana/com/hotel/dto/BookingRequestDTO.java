package diana.com.hotel.dto;

import diana.com.hotel.entity.Room;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class BookingRequestDTO {
    private Date checkInDate;
    private Date checkOutDate;
    private Integer stars;
    private Long hotelId;
    private List<Long> roomsIds;
}
