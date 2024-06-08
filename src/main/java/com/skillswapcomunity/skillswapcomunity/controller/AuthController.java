package com.skillswapcomunity.skillswapcomunity.controller;

import com.skillswapcomunity.skillswapcomunity.domain.RefreshToken;
import com.skillswapcomunity.skillswapcomunity.dto.*;
import com.skillswapcomunity.skillswapcomunity.model.LoginResponse;
import com.skillswapcomunity.skillswapcomunity.model.Person;
import com.skillswapcomunity.skillswapcomunity.service.AuthenticationService;
import com.skillswapcomunity.skillswapcomunity.service.JwtService;
import com.skillswapcomunity.skillswapcomunity.service.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    private RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<Person> register(@RequestBody RegisterUserDto registerUserDto) {
        Person registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        Person authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

    /*
    @PostMapping("/api/v1/logout")
    public void logout() {
        System.out.println("Logout...");
    }*/

}
