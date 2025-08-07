package diana.com.hotel.service;

import diana.com.hotel.exception.UserIllegalArgumentException;
import diana.com.hotel.exception.UserNotFoundException;
import diana.com.hotel.dto.UserInfoRequestDTO;
import diana.com.hotel.entity.ERole;
import diana.com.hotel.entity.UserInfo;
import diana.com.hotel.mapper.UserInfoMapper;
import diana.com.hotel.repository.UserInfoRepository;
import diana.com.hotel.service.impl.UserInfoDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserInfoService implements UserDetailsService {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserInfoMapper userInfoMapper;

    public UserInfo registerUser(UserInfoRequestDTO userInfoRequestDTO) {
        UserInfo entityUserInfo = userInfoMapper.mapUserInfoRequestDTOToUserInfoEntity(userInfoRequestDTO);
        entityUserInfo.setPassword(passwordEncoder.encode(entityUserInfo.getPassword()));
        if (userInfoRepository.existsByEmail(entityUserInfo.getEmail()) ||
                userInfoRepository.existsByName(entityUserInfo.getName())) {
            throw new UserIllegalArgumentException("User already exists");
        }
        return userInfoRepository.save(entityUserInfo);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userDetail = userInfoRepository.findByName(username);
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UserNotFoundException("User" + username +" not found: " ));
    }

    public List<UserInfo> getUsers() {
        return userInfoRepository.findAll();
    }

    public UserInfo getUserById(Long id) {
        return userInfoRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Can not found a user with id: %s".formatted(id)));
    }

    public void deleteUser(String email) {
        UserInfo userInfo = getUser(email);
        userInfoRepository.delete(userInfo);
    }

    public UserInfo getUser(String email) {
        return userInfoRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }

    public UserInfo updateUserDetails(Long id, UserInfoRequestDTO userInfoRequestDTO){
        UserInfo userInfo = getUserById(id);
        updateUserInfoEntity(userInfoRequestDTO, userInfo);
        return userInfoRepository.save(userInfo);

    }

    private void updateUserInfoEntity(UserInfoRequestDTO userInfoRequestDTO, UserInfo userInfo) {
        userInfo.setName(userInfoRequestDTO.getName());
        userInfo.setPassword(userInfoRequestDTO.getPassword());
        userInfo.setEmail(userInfoRequestDTO.getEmail());
        userInfo.setRoles(ERole.valueOf(userInfoRequestDTO.getRole()));
    }

    public void changeRole(String email, ERole newRole) {
        UserInfo userInfo = userInfoRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
        userInfo.setRoles(newRole);
        userInfoRepository.save(userInfo);
    }


}
