package com.skillswapcomunity.skillswapcomunity.service;
import com.skillswapcomunity.skillswapcomunity.dto.PersonDto;
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
    public List<PersonDto> getPersons() {
        return personRepository.findAll().stream().map(this::convertPersonToPersonDto).toList();
    }

    @Override
    public Optional<PersonDto> getPerson(Long id) {
        Optional<Person> personOptional = personRepository.findById(id);
        if(personOptional.isPresent()) {
            return Optional.of(convertPersonToPersonDto(personOptional.get()));
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public PersonDto createPerson(PersonDto personDto) {
        return convertPersonToPersonDto(
                personRepository.save(convertPersonDtoToPerson(personDto)));
    }

    @Override
    public PersonDto updatePerson(PersonDto person, Long id) {
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
            return convertPersonToPersonDto(personRepository.save(personToUpdate));
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

    private PersonDto convertPersonToPersonDto(Person person) {
        return new PersonDto(person.getName(), person.getEmail(), person.getPhone(), person.getDescription(), person.getAchievements(), person.getSkill(), person.getSalary(), person.getRating(), person.getExperience(), person.getCompany());
    }

    private Person convertPersonDtoToPerson(PersonDto personDto) {
        String personName = personDto.getName();
        Person person = personRepository.findByName(personName);
        return  person;
    }
}
