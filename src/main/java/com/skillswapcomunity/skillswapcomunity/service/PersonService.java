package com.skillswapcomunity.skillswapcomunity.service;
import com.skillswapcomunity.skillswapcomunity.dto.PersonDto;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    List<PersonDto> getPersons();
    Optional<PersonDto> getPerson(Long id);
    PersonDto createPerson(PersonDto person);
    PersonDto updatePerson(PersonDto person, Long id);
    void deletePerson(Long id);
}
