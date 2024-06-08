package com.skillswapcomunity.skillswapcomunity.service;

import com.skillswapcomunity.skillswapcomunity.dto.RegisterUserDto;
import com.skillswapcomunity.skillswapcomunity.dto.LoginUserDto;
import com.skillswapcomunity.skillswapcomunity.model.Person;
import com.skillswapcomunity.skillswapcomunity.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public Person signup(RegisterUserDto input) {
        Person person = new Person();
        person.setName(input.getFullName());
        person.setEmail(input.getEmail());
        person.setPassword(passwordEncoder.encode(input.getPassword()));

        return personRepository.save(person);
    }

    public Person authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return personRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}