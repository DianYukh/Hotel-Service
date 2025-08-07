package diana.com.hotel.repository;

import diana.com.hotel.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findUserByEmail(String email);
    Optional<UserInfo> findByName(String name);
    Optional<UserInfo> findById(Long id);
    boolean existsAllBy();
    boolean existsByEmail(String email);
    boolean existsByName(String name);
}
