package com.skillswapcomunity.skillswapcomunity.service;
import com.skillswapcomunity.skillswapcomunity.dto.PersonDto;
import com.skillswapcomunity.skillswapcomunity.model.Company;
import com.skillswapcomunity.skillswapcomunity.model.Person;
import com.skillswapcomunity.skillswapcomunity.model.Requests;
import com.skillswapcomunity.skillswapcomunity.model.Skill;
import com.skillswapcomunity.skillswapcomunity.repository.CompanyRepository;
import com.skillswapcomunity.skillswapcomunity.repository.PersonRepository;
import com.skillswapcomunity.skillswapcomunity.repository.RequestsRepository;
import com.skillswapcomunity.skillswapcomunity.repository.SkillRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;
    private CompanyRepository companyRepository;
    private SkillRepository skillRepository;
    private RequestsRepository requestRepository;

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
    public Optional<Person> getPersonEntity(Long id) {
        return personRepository.findById(id);
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
            personToUpdate.setSentRequests(person.getSentRequests());
            personToUpdate.setReceivedRequests(person.getReceivedRequests());
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
        return new PersonDto(person.getId(), person.getName(), person.getEmail(), person.getPhone(), person.getDescription(), person.getAchievements(), person.getSkill(), person.getSalary(), person.getRating(), person.getExperience(), person.getCompany(), person.getSentRequests(), person.getReceivedRequests());
    }

    private Person convertPersonDtoToPerson(PersonDto personDto) {
        Person person = new Person();
        person.setName(personDto.getName());
        person.setEmail(personDto.getEmail());
        person.setPhone(personDto.getPhone());
        person.setDescription(personDto.getDescription());
        person.setAchievements(personDto.getAchievements());

        if (personDto.getSkill() != null && personDto.getSkill().getId() != null) {
            Skill skill = skillRepository.findById(personDto.getSkill().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Skill not found"));
            person.setSkill(skill);
        }

        if (personDto.getCompany() != null && personDto.getCompany().getId() != null) {
            Company company = companyRepository.findById(personDto.getCompany().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Company not found"));
            person.setCompany(company);
        }

        person.setSalary(personDto.getSalary());
        person.setRating(personDto.getRating());
        person.setExperience(personDto.getExperience());
        person.setSentRequests(personDto.getSentRequests());
        person.setReceivedRequests(personDto.getReceivedRequests());
        return person;
    }
}
