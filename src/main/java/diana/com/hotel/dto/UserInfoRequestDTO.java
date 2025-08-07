package diana.com.hotel.dto;

import lombok.Data;

@Data
public class UserInfoRequestDTO {
    private String name;
    private String password;
    private String email;
    private String role;
}
