package diana.com.hotel.controller;

import diana.com.hotel.dto.UserInfoRequestDTO;
import diana.com.hotel.entity.AuthRequest;
import diana.com.hotel.entity.AuthResponse;
import diana.com.hotel.entity.UserInfo;
import diana.com.hotel.exception.UserNotFoundException;
import diana.com.hotel.service.JwtService;
import diana.com.hotel.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserInfoService userInfoService;

    @PostMapping("/registerUser")
    public UserInfo registerUser(@RequestBody UserInfoRequestDTO userInfoRequestDTO) {
        return userInfoService.registerUser(userInfoRequestDTO);
    }

//    @PostMapping("/login")
//    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
//        );
//        if (authentication.isAuthenticated()) {
//            UserDetails userDetails = userInfoService.loadUserByUsername(authRequest.getUsername());
//            return jwtService.generateToken(userDetails);
//        } else {
//            throw new UserNotFoundException("Invalid user request!");
//        }
//    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtService.generateToken(userDetails);

            return ResponseEntity.ok(new AuthResponse(token));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse("Invalid credentials"));
        }
    }



    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/setAdmin")
    public UserInfo registerAdmin(@RequestBody UserInfoRequestDTO userInfoRequestDTO) {
        userInfoRequestDTO.setRole("ROLE_ADMIN");
        return userInfoService.registerUser(userInfoRequestDTO);
    }
}
