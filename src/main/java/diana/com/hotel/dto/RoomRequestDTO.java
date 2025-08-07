package diana.com.hotel.dto;

import diana.com.hotel.entity.ERoomType;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class RoomRequestDTO {
    private int numberOfGuests;
    private BigDecimal costPerNight;
    private ERoomType type;
}
