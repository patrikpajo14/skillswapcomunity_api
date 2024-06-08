package com.skillswapcomunity.skillswapcomunity.repository;

import com.skillswapcomunity.skillswapcomunity.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByName(String name);
    Optional<Person> findByEmail(String email);
}
