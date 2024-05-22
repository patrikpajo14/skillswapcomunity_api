package com.skillswapcomunity.skillswapcomunity.controller;

import com.skillswapcomunity.skillswapcomunity.dto.PersonDto;
import com.skillswapcomunity.skillswapcomunity.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {

    private PersonService personService;

    @GetMapping("/all")
    public List<PersonDto> getAllPersons() {
        return personService.getPersons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> getPersonById(@PathVariable long id) {
        Optional<PersonDto> personOptional = personService.getPerson(id);
        if (personOptional.isPresent()) {
            return ResponseEntity.ok(personOptional.get());
        }
        else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<PersonDto> createPerson(@RequestBody PersonDto person) {
        return new ResponseEntity<>(
                personService.createPerson(person),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDto> updatePerson(@RequestBody PersonDto person, @PathVariable Long id)
    {
        try {
            PersonDto updatedPerson = personService.updatePerson(person, id);
            return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }
}