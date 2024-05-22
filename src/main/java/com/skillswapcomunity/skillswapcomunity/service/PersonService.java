package com.skillswapcomunity.skillswapcomunity.service;
import com.skillswapcomunity.skillswapcomunity.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    List<Person> getPersons();
    Optional<Person> getPerson(Long id);
    Person createPerson(Person position);
    Person updatePerson(Person position, Long id);
    void deletePerson(Long id);
}
