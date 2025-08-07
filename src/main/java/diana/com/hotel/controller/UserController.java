package diana.com.hotel.controller;



import diana.com.hotel.dto.UserInfoRequestDTO;
import diana.com.hotel.entity.ERole;
import diana.com.hotel.entity.UserInfo;
import diana.com.hotel.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    public static final String EMAIL = "email";
    private final UserInfoService userInfoService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/all")
    public List<UserInfo> getUsers() {
        return userInfoService.getUsers();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/getUser/{id}")
    public UserInfo getUserById(@PathVariable Long id) {
        return userInfoService.getUserById(id);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/deleteUser/{email}")
    public String deleteUser(@PathVariable(EMAIL) String email) {
        userInfoService.deleteUser(email);
        return "User with email " + email + " deleted successfully.";
    }

    @PutMapping("/updateUser/{id}")
    public UserInfo updateUserDetails(@PathVariable Long id, @RequestBody UserInfoRequestDTO userInfoRequestDTO){
        return userInfoService.updateUserDetails(id, userInfoRequestDTO);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/changeRole/{email}/{newRole}")
    public String changeRole(@PathVariable(EMAIL) String email, @PathVariable String newRole) {
        ERole roles = ERole.valueOf(newRole);
        userInfoService.changeRole(email, roles);
        return "Role is changed successfully ";
    }
}
