package com.skillswapcomunity.skillswapcomunity.service;
import com.skillswapcomunity.skillswapcomunity.model.Person;
import com.skillswapcomunity.skillswapcomunity.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    @Override
    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    @Override
    public Optional<Person> getPerson(Long id) {
        return personRepository.findById(id);
    }

    @Override
    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person updatePerson(Person person, Long id) {
        Optional<Person> personOptional = personRepository.findById(id);
        if (personOptional.isPresent()) {
            Person personToUpdate = personOptional.get();
            personToUpdate.setName(person.getName());
            personToUpdate.setEmail(person.getEmail());
            personToUpdate.setPhone(person.getPhone());
            personToUpdate.setDescription(person.getDescription());
            personToUpdate.setAchievements(person.getAchievements());
            personToUpdate.setSkill(person.getSkill());
            personToUpdate.setSalary(person.getSalary());
            personToUpdate.setRating(person.getRating());
            personToUpdate.setExperience(person.getExperience());
            personToUpdate.setCompany(person.getCompany());
            return personRepository.save(personToUpdate);
        }
        else {
            throw new EntityNotFoundException("Person with the ID = '"
                    + id + "' not found");
        }
    }

    @Override
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
}
