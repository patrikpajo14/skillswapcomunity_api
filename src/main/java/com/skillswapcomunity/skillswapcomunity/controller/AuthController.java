package com.skillswapcomunity.skillswapcomunity.controller;

import com.skillswapcomunity.skillswapcomunity.dto.*;
import com.skillswapcomunity.skillswapcomunity.model.LoginResponse;
import com.skillswapcomunity.skillswapcomunity.model.Person;
import com.skillswapcomunity.skillswapcomunity.service.AuthenticationService;
import com.skillswapcomunity.skillswapcomunity.service.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

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
        loginResponse.setUser(authenticatedUser);

        return ResponseEntity.ok(loginResponse);
    }
}
