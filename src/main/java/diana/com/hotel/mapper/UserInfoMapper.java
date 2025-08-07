package diana.com.hotel.mapper;

import diana.com.hotel.dto.UserInfoRequestDTO;
import diana.com.hotel.entity.ERole;
import diana.com.hotel.entity.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class UserInfoMapper {
    public UserInfo mapUserInfoRequestDTOToUserInfoEntity(UserInfoRequestDTO userInfoRequestDTO){
        ERole roles;
        try {
            roles = ERole.valueOf(userInfoRequestDTO.getRole());
        } catch (IllegalArgumentException | NullPointerException e) {
          roles = ERole.ROLE_USER;
        }
        return UserInfo.builder()
                .name(userInfoRequestDTO.getName())
                .password(userInfoRequestDTO.getPassword())
                .email(userInfoRequestDTO.getEmail())
                .roles(roles)
                .build();
    }
}
